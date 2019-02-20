package com.luckwine.pgw.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.luckwine.pgw.dao.PayOrderMapper;
import com.luckwine.pgw.model.base.PayOrder;
import com.luckwine.pgw.model.enums.OrderSyncStatus;
import com.luckwine.pgw.model.enums.PayOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Slf4j
public class PayOrderSyncOrderJob implements SimpleJob {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Value("${maxSyncOrderCount}")
    private int maxSyncOrderCount;


    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("订单同步状态稽核");
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date();
        calendar.setTime(curDate);
        calendar.add(Calendar.DATE, - 1);

        Date startTime = calendar.getTime();

        calendar.setTime(curDate);
        calendar.add(Calendar.MINUTE, - 1);

        Date endTime = calendar.getTime();

        //查询1天前 到 1分钟前的 支付成功   未同步 或者 同步失败  , 小于最大同步次数的订单

        Example example = new Example(PayOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", PayOrderStatus.SUCCESS);
        List<String> syncStatus = new ArrayList<>();
        syncStatus.add(OrderSyncStatus.SYNC_WAIT.name());
        syncStatus.add(OrderSyncStatus.SYNC_FAIL.name());
        criteria.andIn("orderSyncStatus", syncStatus);
        criteria.andBetween("createTime", startTime, endTime);
        List<PayOrder> payOrders = payOrderMapper.selectByExample(example);
        String startTimeStr = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(startTime);
        String endTimeStr = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(endTime);
        log.info("[{}-{}]查询到超时未支付单数:{}",startTimeStr, endTimeStr, payOrders.size());

        for (PayOrder payOrder : payOrders) {
            log.info("开始同步状态到订单:{}", payOrder);
            payOrder.setOrderSyncCount(payOrder.getOrderSyncCount() + 1);
            payOrder.setUpdateTime(new Date());
            //更新同步状态
            //payOrder.setOrderSyncStatus();
            payOrderMapper.updateByPrimaryKeySelective(payOrder);
        }

    }
}
