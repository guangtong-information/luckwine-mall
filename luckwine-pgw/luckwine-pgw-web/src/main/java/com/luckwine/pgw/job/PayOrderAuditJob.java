package com.luckwine.pgw.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.luckwine.pgw.dao.PayOrderMapper;
import com.luckwine.pgw.model.base.PayOrder;
import com.luckwine.pgw.model.enums.PayOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class PayOrderAuditJob implements SimpleJob {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Value("${maxPollingCount}")
    private int maxPollingCount;

    @Override
    public void execute(ShardingContext shardingContext) {
        String supplierCode = shardingContext.getShardingParameter();
        log.info("支付单稽核:{}", supplierCode);
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date();
        calendar.setTime(curDate);
        calendar.add(Calendar.DATE, - 2);

        Date startTime = calendar.getTime();

        calendar.setTime(curDate);
        calendar.add(Calendar.MINUTE, - 5);

        Date endTime = calendar.getTime();

        //查询2天前 到 5分钟前的  创建状态的支付单 , 小于最大轮询次数的订单

        Example example = new Example(PayOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", PayOrderStatus.CREATE);
        criteria.andBetween("createTime", startTime, endTime);
        criteria.andLessThan("pollingCount", maxPollingCount);
        criteria.andEqualTo("supplierCode", supplierCode);
        List<PayOrder> payOrders = payOrderMapper.selectByExample(example);

        String startTimeStr = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(startTime);
        String endTimeStr = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(endTime);
        log.info("[{}-{}]查询到超时未支付单数:{}",startTimeStr, endTimeStr, payOrders.size());

        for (PayOrder payOrder : payOrders) {
            log.info("开始稽核单: {}", payOrder);
            log.info("调用供应商接口查询 支付状态");
            log.info("1.更新支付状态 ");
            log.info("2.如果支付成功，回调交易模块");
            log.info("3.如果状态未确认，继续查询");
            payOrder.setPollingCount(payOrder.getPollingCount() + 1);
            payOrder.setUpdateTime(new Date());
            payOrderMapper.updateByPrimaryKeySelective(payOrder);


        }
    }
}
