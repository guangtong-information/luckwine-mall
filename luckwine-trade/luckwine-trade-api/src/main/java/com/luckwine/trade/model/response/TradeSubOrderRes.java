package com.luckwine.trade.model.response;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
public class TradeSubOrderRes {
    /**
     * 子单号
     */
    private String subOrderCode;

    /**
     * 主单号
     */
    private String mainOrderCode;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 买家登录账号,对应cust_info表
     */
    private String buyLoginAccount;

    /**
     * 卖家登录账号,对应cust_info表
     */
    private String saleLoginAccount;

    /**
     * 订单总金额(实付金额+手续费-优惠金额+运费)
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
     * 商品数
     */
    private Long goodsCount;

    /**
     * 备注
     */
    private String note;

}