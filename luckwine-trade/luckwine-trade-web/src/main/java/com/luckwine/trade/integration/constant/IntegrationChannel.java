package com.luckwine.trade.integration.constant;

/**
 * 工作流消息通道名称
 * Created by Winlone on 2018/9/20.
 */
public class IntegrationChannel {

    /**
     * 消费渠道
     */
    public static final String CONSUME_ORDER_CHANNEL = "consumeOrderChannel";

    /**
     * 充值渠道
     */
    public static final String RECHARGE_ORDER_CHANNEL = "rechargeOrderChannel";

    /**
     * 余额支付渠道
     */
    public static final String BALANCE_PAY_CHANNEL = "balancePayChannel";

    /**
     * 支付宝支付渠道
     */
    public static final String ALIPAY_CHANNEL = "alipayChannel";

    /**
     * 支付回调渠道
     */
    public static final String PAY_BACK_CHANNEL = "payBackChannel";



    /* 结果转换 */
    public static final String CONVERT_RESULT_CHANNEL = "convertResultChannel";


}
