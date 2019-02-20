package com.luckwine.pgw.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "pay_order_diff")
public class PayOrderDiff implements Serializable {

    @Id
    private Long id;

    /** 账单日 */
    private String billDate;

    /** 批次号 */
    private String batchCode;

    /** 支付单号 */
    private String payOrderNo;

    /** 支付单金额*/
    private BigDecimal payOrderAmount;

    /**供应商订单号 */
    private String supplierOrderNo;

    /** 供应商金额 */
    private BigDecimal supplierOrderAmount;

    /** 差异类型 */
    private String diffType;

    /**状态*/
    private String status;

    /** 创建时间 */
    private Date createTime;

    /**  更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;






}
