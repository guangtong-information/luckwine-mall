package com.luckwine.trade.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
public class TradeCapitalStatement {
    /**
     * 订单模块-资金流水号
     */
    @Id
    private String capSeq;

    /**
     * 资金系统返回流水号
     */
    private String capBackSeq;

    /**
     * 资金系统返回响应编码
     */
    private String capBackCode;

    /**
     * 资金系统返回响应描述
     */
    private String capBackDesc;

    /**
     * 主单号
     */
    private String mainOrderCode;

    /**
     * 子单号
     */
    private String subOrderCode;

    /**
     * 余额支付账号/第三方交易账号/营销支付账号
     *
     */
    private String capAcctCode;

    /**
     * 交易操作:PAY-付款,RECEIVE-收款
     */
    private String payOper;

    /**
     * 资金模块来源:ACCT、MARKETING、PGW
     */
    private String capModule;

    /**
     * 资金系统类型：BALANCE、ALLINPAY、ALIPAY、COUPON
     */
    private String capSysType;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 支付状态：等待支付、支付成功、支付失败、支付超时
     */
    private String payStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 支付时间，支付平台返回
     */
    private Date payTime;
}