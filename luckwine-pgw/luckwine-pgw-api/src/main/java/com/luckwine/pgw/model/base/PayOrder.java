package com.luckwine.pgw.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Table(name = "pay_order")
public class PayOrder implements Serializable {

    @Id
    private Long id;

    /** 支付单号 */
    private String payOrderNo;

    /** 订单号 -- 订单模块 */
    private String orderNo;

    /**  支付单金额 */
    private BigDecimal amount;

    /** 资金流水 --订单 */
    private String capSeq;

    /** 用户账号 */
    private String userAccount;

    /** 支付单标题 */
    private String subject;

    /** 支付单描述 */
    private String body;

    /**  支付类型 */
    private String payType;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商对应用户的账号 */
    private String supplierUserAccount;

    /** 供应商订单号 */
    private String supplierOrderNo;

    /** 供应商订单状态 */
    private String supplierOrderStatus;

    /** 支付单状态*/
    private String status;

    /** 订单同步状态 */
    private String orderSyncStatus;

    /** 备注 */
    private String remark;

    /**支付时间 */
    private Date payTime;

    /** 轮询次数 */
    private Integer pollingCount;

    /** 同步订单次数 */
    private Integer orderSyncCount;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;



}
