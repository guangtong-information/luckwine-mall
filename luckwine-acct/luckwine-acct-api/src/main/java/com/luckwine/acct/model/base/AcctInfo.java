package com.luckwine.acct.model.base;

import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户列表响应参数
 */
@Data
@ToString(callSuper = true)
@Table(name = "acct_info")
public class AcctInfo extends BaseResponse implements Serializable {

    /**
     * 账户编号
     */
    @Id
    private String acctCode;

    /**
     * 账户名称
     */
    private String acctName;

    /**
     * 客户登录账号,关联cust_info表
     */
    private String loginAccount;

    /**
     * 账户类型 关联acct_accttype_dict表
     */
    private String acctTypeCode;

    /**
     * 可用金额
     */
    private BigDecimal availBal;

    /**
     * 状态：00可用 01冻结 02销户
     */
    private String stat;

    /**
     * 冻结日期
     */
    private Date frozenDate;

    /**
     * 余额限额
     */
    private BigDecimal balLimit;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
