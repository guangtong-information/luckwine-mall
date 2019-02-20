package com.luckwine.pgw.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "pay_order_diff_batch")
public class PayOrderDiffBatch implements Serializable {

    @Id
    private Long id;

    /** 批次id */
    private String batchCode;

    /** 账单日 */
    private String billDate;

    /** 供应商编码 */
    private String supplierCode;

    /** 支付单数 */
    private Integer payOrderCount;

    /** 供应商订单数 */
    private Integer supplOrderCount;

    /** 长款数 */
    private Integer excessMoneyCount;

    /** 短款数 */
    private Integer lackMoneyCount;

    /**  差额数 */
    private Integer diffMoneyCount;

    /** 状态 */
    private String status;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
