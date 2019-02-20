package com.luckwine.trade.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
public class TradeMainOrder {
    /**
     * 主单号
     */
    @Id
    private String mainOrderCode;

    /**
     * 交易类型:RECHARGE("RECHARGE", "充值"),WITHDRAW("WITHDRAW", "提现"),TRANSFER("TRANSFER", "转账"),CONSUME("CONSUME", "消费");
     */
    private String transType;

    /**
     * 订单状态:PAYMENT_WAITING("PAYMENT_WAITING","等待支付"),PAYMENT_SUCCEED("PAYMENT_SUCCEED","支付成功"),PAYMENT_FAIL("PAYMENT_FAIL","支付失败"),PAYMENT_TIMEOUT("PAYMENT_TIMEOUT","支付超时"),ORDER_CANCEL("ORDER_CANCEL","订单取消"),ORDER_FINISHED("ORDER_FINISHED","订单完成");
     */
    private String orderStatus;

    /**
     * 渠道编码 PORTALWEB("WINELUCK-PORTAL-WEB", "酒缘网-PC版"),OSSWEB("WINELUCK-OSS-WEB", "酒缘网运管系-PC版")
     */
    private String channelCode;

    /**
     * 买家登录账号,对应cust_info表
     */
    private String buyLoginAccount;

    /**
     * 订单总金额
     * (实付金额+手续费-优惠金额+运费)
     */
    private BigDecimal totalAmount;

    /**
     * 实付金额
     */
    private BigDecimal payAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discAmount;

    /**
     * 运费
     */
    private BigDecimal freightAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 备注
     */
    private String note;

}