package com.luckwine.acct.model.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
@Table(name = "acct_expenses_detail")
public class ExpensesDetail {
    /**
     * 账户流水号
     */
    private String acctOrderno;

    /**
     * 账户请求流水号（订单模块传入）
     */
    private String requestSeq;

    /**
     * 关联账户号
     */
    private String acctCode;

    /**
     * 收支类型
     */
    private String expenseType;

    /**
     * 账户总余额
     */
    private BigDecimal totalBal;

    /**
     * 交易金额
     */
    private BigDecimal trsAmount;

    /**
     * 交易类型:充值、提现、消费
     */
    private String transType;

    /**
     * 交易时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 交易摘要
     */
    private String summary;

    /**
     * 外部系统订单号(订单模块的主单号)
     */
    private String extTrsSeq;


}