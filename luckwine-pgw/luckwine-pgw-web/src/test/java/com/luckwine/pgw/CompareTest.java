package com.luckwine.pgw;


import com.alipay.api.AlipayApiException;
import com.luckwine.pgw.dao.PayOrderDiffBatchMapper;
import com.luckwine.pgw.dao.PayOrderDiffMapper;
import com.luckwine.pgw.job.PayOrderVerifyBillJob;
import com.luckwine.pgw.model.base.PayOrderDiff;
import com.luckwine.pgw.model.base.PayOrderDiffBatch;
import com.luckwine.pgw.model.enums.DiffBatchStatus;
import com.luckwine.pgw.model.enums.DiffStatus;
import com.luckwine.pgw.model.enums.DiffType;
import com.luckwine.pgw.model.enums.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PgwApplication.class)
@Slf4j
public class CompareTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PayOrderDiffMapper payOrderDiffMapper;

    @Autowired
    private PayOrderDiffBatchMapper payOrderDiffBatchMapper;

    @Autowired
    private PayOrderVerifyBillJob job;

    /*
     * 批量导入数据到redis
     * */
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < 1000; i++) {
                    long bill = 10000000;
                    connection.setCommands().sAdd("bill_1".getBytes(), String.valueOf(bill + i).getBytes());
                }
                return null;
            }
        });
        System.out.println("耗时" + (System.currentTimeMillis() - start));
    }


    /*
     * 逐条导入redis
     * */
    @Test
    public void test2() {
        long start2 = System.currentTimeMillis();
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < 1000; i++) {
                    long bill = 10000000;
                    connection.setCommands().sAdd("bill_2".getBytes(), String.valueOf(bill + i).getBytes());
                }
                return null;
            }
        });
        System.out.println("耗时" + (System.currentTimeMillis() - start2));
    }


    /**
     * 对账
     */
    @Test
    public void test3() {
        //供应商编码:支付单号:供应商订单号:支付单金额
        //SELECT  CONCAT( supplier_code, ":", pay_order_no ,":", supplier_order_no, ":", amount )  FROM `pay_order` where status = 'SUCCESS';
        List<String> payOrders = new ArrayList<String>();
        payOrders.add("ALIPAY:3011810111041051615237012:2018101121001004310500311358:102.00");
        payOrders.add("ALIPAY:3011810111045061885242015:2018101122001434310500312353:103.00");
        payOrders.add("ALIPAY:3011810111049520059635009:2018101122001434310500312354:107.00");
        payOrders.add("ALIPAY:3011810111408283792864003:2018101122001434310500312358:106.00");

        payOrders.add("ALIPAY:3011810111408283792864004:2018101122001434310500312359:107.00"); //短款
        payOrders.add("ALIPAY:3011810111045061885242018:2018101122001434310500312355:105.00"); //差额


        List<String> supplOrders = new ArrayList<>();
        supplOrders.add("ALIPAY:3011810111041051615237012:2018101121001004310500311358:102.00");
        supplOrders.add("ALIPAY:3011810111045061885242015:2018101122001434310500312353:103.00");
        supplOrders.add("ALIPAY:3011810111049520059635009:2018101122001434310500312354:107.00");
        supplOrders.add("ALIPAY:3011810111408283792864003:2018101122001434310500312358:106.00");

        supplOrders.add("ALIPAY:3011810111041051615237013:2018101121001004310500311358:103.00");  //长款
        supplOrders.add("ALIPAY:3011810111045061885242018:2018101122001434310500312355:106.00"); //差额

        String billDate = "20181011";
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


        //导入支付单数据
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(payOrderKey.getBytes());
                for (String str : payOrders) {
                    connection.setCommands().sAdd(payOrderKey.getBytes(), str.getBytes());
                    connection.expire(supplKey.getBytes(), 600);//设置过期时间
                }
                return null;
            }
        });

        //导入供应商数据
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(supplKey.getBytes());
                for (String str : supplOrders) {
                    connection.setCommands().sAdd(supplKey.getBytes(), str.getBytes());
                    connection.expire(supplKey.getBytes(), 600);
                }
                return null;
            }
        });

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


    @Test
    public void testAlipayBillDownload() throws AlipayApiException {

        job.execute(null);
    }


}
