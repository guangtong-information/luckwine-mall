package com.luckwine.trade.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * 取消订单服务
 */
@Slf4j
public class CancelOrderJob implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {

        log.info("取消订单:{}", "取消超过24小时未支付的订单");

    }
}
