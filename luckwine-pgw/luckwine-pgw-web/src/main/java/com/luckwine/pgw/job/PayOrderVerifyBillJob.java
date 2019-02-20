package com.luckwine.pgw.job;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.luckwine.pgw.config.AlipayConfig;
import com.luckwine.pgw.dao.PayOrderDiffBatchMapper;
import com.luckwine.pgw.dao.PayOrderDiffMapper;
import com.luckwine.pgw.dao.PayOrderMapper;
import com.luckwine.pgw.model.base.PayOrderDiff;
import com.luckwine.pgw.model.base.PayOrderDiffBatch;
import com.luckwine.pgw.model.enums.DiffBatchStatus;
import com.luckwine.pgw.model.enums.DiffStatus;
import com.luckwine.pgw.model.enums.DiffType;
import com.luckwine.pgw.model.enums.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.example.SelectOneByExampleMapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PayOrderVerifyBillJob implements SimpleJob {

    //    private static final String SUFFIX = ".csv.zip";
//    private static final String FILE_PATH = "D:" + File.separator + "dowloads" + File.separator + "test" + File.separator;
    private static final String BILL_VALUE_FORMAT = "ALIPAY:%s:%s:%s";
    @Resource
    private AlipayConfig alipayConfig;
    @Resource
    private PayOrderMapper payOrderMapper;
    @Resource
    private PayOrderDiffBatchMapper payOrderDiffBatchMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private PayOrderDiffMapper payOrderDiffMapper;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("对账处理， 详细处理见对支付单对账.puml流程图");
        //        String date = DateUtil.format(new Date(), "yyyy-MM-dd");
        String billDate = "2018-04-05";
        String savePath = alipayConfig.getBillSavePath();
        try {
            //下载对账单
            alipayBillDownload(billDate, savePath);

            //解压缩文件
            //D:\dowloads\test\20881021757775880156_20180405.csv.zip
            String zipPath = savePath + alipayConfig.getUserAccount() + "_" + billDate.replaceAll("-", "");
            File unzip = ZipUtil.unzip(zipPath + alipayConfig.getBillSuffix(), zipPath, Charset.forName("GBK"));
            //设置字符集
            Charset charset = Charset.forName("GBK");
            if (unzip != null && unzip.listFiles().length > 0) {
                CsvReader reader = CsvUtil.getReader();
                CsvData data = reader.read(unzip.listFiles()[0], charset);
                //获取交易汇总中的交易订单总数
                int trandNum = getTradeNum(data);
                data = reader.read(unzip.listFiles()[1], charset);
                compare(billDate,trandNum,data);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对账
     * @param billDate
     * @param trandNum
     * @param billData
     */
    private void compare(String billDate, int trandNum, CsvData billData) {
        //获取订单数据
        List<String> payOrders = payOrderMapper.selectPayOrders(billDate);
        //解析供应商数据
        List<String> supplOrders = getSupplOrders(billData, trandNum);

        String supplierCode = Supplier.ALIPAY.name();
        //1,查询批次
        String batchCode = supplierCode + "_" + billDate;
        Example example = new Example(PayOrderDiffBatch.class);
        example.createCriteria().andEqualTo("batchCode", batchCode);

        PayOrderDiffBatch qryDiffBatch = payOrderDiffBatchMapper.selectOneByExample(example);
        if (qryDiffBatch != null) {
            batchCode = qryDiffBatch.getBatchCode() + "_" + DateFormatUtils.format(new Date(), "HHmmss");
        }

        String payOrderKey = "CHECK_PAY_" + batchCode;
        String supplKey = "CHECK_SUPPL_" + batchCode;

        //存入Redis
        pushRedis(payOrderKey,payOrders);
        pushRedis(supplKey,supplOrders);

        //获取短款
        Set<byte[]> lackSets = (Set<byte[]>) redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setCommands().sDiff(payOrderKey.getBytes(), supplKey.getBytes());
            }
        });

        //获取长款
        Set<byte[]> excessSets = (Set<byte[]>) redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setCommands().sDiff(supplKey.getBytes(), payOrderKey.getBytes());
            }
        });



        List<PayOrderDiff> list = new ArrayList<>();
        for (byte[] lack : lackSets) {
            PayOrderDiff difference = new PayOrderDiff();
            String[] payOrderAray = new String(lack).split(":");
            difference.setPayOrderNo(payOrderAray[1]);
            difference.setSupplierOrderNo(payOrderAray[2]);
            difference.setPayOrderAmount(new BigDecimal(payOrderAray[3]));
            difference.setDiffType(DiffType.LACK_MONEY.name());
            difference.setBillDate(billDate);
            difference.setBatchCode(batchCode);
            difference.setStatus(DiffStatus.WAIT.name());
            difference.setCreateTime(new Date());
            list.add(difference);
        }

        for (byte[] excess : excessSets) {
            PayOrderDiff difference = new PayOrderDiff();
            String[] payOrderAray = new String(excess).split(":");
            difference.setPayOrderNo(payOrderAray[1]);
            difference.setSupplierOrderNo(payOrderAray[2]);
            difference.setSupplierOrderAmount(new BigDecimal(payOrderAray[3]));
            difference.setDiffType(DiffType.EXCESS_MONEY.name());
            difference.setBillDate(billDate);
            difference.setBatchCode(batchCode);
            difference.setStatus(DiffStatus.WAIT.name());
            difference.setCreateTime(new Date());
            list.add(difference);
        }

     /*   List<String> strs = list.stream().collect(Collectors.toMap(PayOrderDiff::getPayOrderNo, PayOrderDiff -> 1, (a, c) -> a + c)).
                entrySet().stream().filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());*/


        //获取出 差额的订单号
        List<String> diffPayOrderNos = list.stream().collect(Collectors.groupingBy(PayOrderDiff::getPayOrderNo, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey())
                .collect(Collectors.toList());

        //获取所有的差额单
        List<PayOrderDiff> diffPayOrders = list.stream().filter(entry ->  diffPayOrderNos.contains(entry.getPayOrderNo())).collect(Collectors.toList());

        //剔除这些数据
        list.removeAll(diffPayOrders);

        // 组建为差额单
        Map<String, List<PayOrderDiff>> map = diffPayOrders.stream().collect(Collectors.groupingBy(PayOrderDiff::getPayOrderNo));

        for (String key : map.keySet()) {
            List<PayOrderDiff> diffs = map.get(key);
            PayOrderDiff diff = new PayOrderDiff();
            diff.setPayOrderNo(diffs.get(0).getPayOrderNo());
            diff.setSupplierOrderNo(diffs.get(0).getSupplierOrderNo());
            diff.setPayOrderAmount(diffs.get(0).getPayOrderAmount() == null ? diffs.get(1).getPayOrderAmount()  : diffs.get(0).getPayOrderAmount());
            diff.setSupplierOrderAmount(diffs.get(0).getSupplierOrderAmount() == null ? diffs.get(1).getSupplierOrderAmount() : diffs.get(0).getSupplierOrderAmount() );
            diff.setDiffType(DiffType.DIFF_MONEY.name());
            diff.setBillDate(billDate);
            diff.setBatchCode(batchCode);
            diff.setStatus(DiffStatus.WAIT.name());
            diff.setCreateTime(new Date());
            list.add(diff);
        }

        if (!list.isEmpty()) {
            payOrderDiffMapper.insertList(list);
        }

        PayOrderDiffBatch diffBatch = new PayOrderDiffBatch();
        diffBatch.setBatchCode(batchCode);
        diffBatch.setBillDate(billDate);
        diffBatch.setPayOrderCount(payOrders.size());
        diffBatch.setSupplOrderCount(supplOrders.size());
        diffBatch.setExcessMoneyCount(excessSets.size() - diffPayOrderNos.size());
        diffBatch.setLackMoneyCount(lackSets.size() - diffPayOrderNos.size());
        diffBatch.setDiffMoneyCount(diffPayOrderNos.size());
        diffBatch.setSupplierCode(supplierCode);
        diffBatch.setCreateTime(new Date());
        if (list.isEmpty()) {
            diffBatch.setStatus(DiffBatchStatus.OK.name());
        } else {
            diffBatch.setStatus(DiffBatchStatus.ERROR.name());
        }

        payOrderDiffBatchMapper.insertSelective(diffBatch);

    }

    /**
     * 把数据存入Redis
     * @param key
     * @param data
     */
    private void pushRedis(String key,List<String> data){
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key.getBytes());
                for (String str : data) {
                    connection.setCommands().sAdd(key.getBytes(), str.getBytes());
                    //设置过期时间
                    connection.expire(key.getBytes(), 600);
                }
                return null;
            }
        });
    }


    /**
     * 供应商数据
     */
    private List<String> getSupplOrders(CsvData data, int trandNum) {
        List<String> supplOrders = new ArrayList<>();
        if (data == null || data.getRowCount() == 0) {
            return supplOrders;
        }
        //从第5行开始读数据
        int startRow = 4;
        for (int i = startRow; i < data.getRows().size(); i++) {
            CsvRow row = data.getRow(i);
            //row.get(1):商户订单号
            //row.get(0):支付宝交易号
            //row.get(12):商家实收（元）
            supplOrders.add(String.format(BILL_VALUE_FORMAT, row.get(1), row.get(0), row.get(12)));
        }
        return supplOrders;
    }

    /**
     * 获取交易订单总笔数
     *
     * @param data
     * @return
     */
    public static int getTradeNum(CsvData data) {
        return Integer.valueOf(data.getRow(5).get(2).trim()).intValue();
    }

    /**
     * 下载对账单
     *
     * @param date
     * @throws AlipayApiException
     */
    public void alipayBillDownload(String date, String savePath) throws AlipayApiException {
        //请求对账单下载Url
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getServerUrl(),
                alipayConfig.getAppId(), alipayConfig.getPrivateKey(),
                alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getPublicKey(), alipayConfig.getSignType());
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent("{" +
                "\"bill_type\":\"trade\"," +
                "\"bill_date\":\"" + date + "\"" +
                "  }");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            log.info("alipayClient.execute(request)调用成功,下载Url:{}", response.getBillDownloadUrl());
            //下载文件
            HttpUtil.downloadFile(response.getBillDownloadUrl(), FileUtil.file(savePath));
        } else {
            log.info("alipayClient.execute(request)调用失败");
        }
    }


    public static void main(String[] args) {
        File unzip = ZipUtil.unzip("D:\\dowloads\\test\\20881021757775880156_20180405.csv.zip", "D:\\dowloads\\test\\20881021757775880156_20180405", Charset.forName("GBK"));
//        File unzip = FileUtil.file("D:\\dowloads\\test\\20881021757775880156_20180405");

        if (unzip != null && unzip.listFiles().length > 0) {
            CsvReader reader = CsvUtil.getReader();
            CsvData data = reader.read(unzip.listFiles()[0], Charset.forName("GBK"));
            System.out.println("dddddddddddd:" + getTradeNum(data));
//            for (int i = 1; i < unzip.listFiles().length; i++) {
//                CsvData data = reader.read(unzip.listFiles()[i], Charset.forName("GBK"));
//                System.out.println(data.getRows());
//            }
        }
    }


}

