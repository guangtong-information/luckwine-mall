package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;


/**
 *
 * 订单查询
 */
@Data
@ToString(callSuper = true)
public class QueryMainOrderRequest extends BaseRequest {

    /**
     * 主单号
     */
    private String mainOrderCode;

    /**
     * 子单号
     */
    private String subOrderCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 买家登录账号,对应cust_info表
     */
    private String buyLoginAccount;

    /**
     * 卖家登录账号,对应cust_info表
     */
    private String saleLoginAccount;

    /**
     * 交易类型:RECHARGE("RECHARGE", "充值"),WITHDRAW("WITHDRAW", "提现"),TRANSFER("TRANSFER", "转账"),CONSUME("CONSUME", "消费");
     */
    private String transType;

    /**
     * 订单状态:PAYMENT_WAITING("PAYMENT_WAITING","等待支付"),PAYMENT_SUCCEED("PAYMENT_SUCCEED","支付成功"),PAYMENT_FAIL("PAYMENT_FAIL","支付失败"),PAYMENT_TIMEOUT("PAYMENT_TIMEOUT","支付超时"),ORDER_CANCEL("ORDER_CANCEL","订单取消"),ORDER_FINISHED("ORDER_FINISHED","订单完成");
     */
    private String orderStatus;

    /**
     * 下单开始时间
     */
    private String createTimeStart;

    /**
     * 下单结束时间
     */
    private String createTimeEnd;
}
