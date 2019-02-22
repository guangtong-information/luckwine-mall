package com.luckwine.portal.acct.model.response;

import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户列表响应参数
 */
@Data
@ToString(callSuper = true)
public class AcctInfoOssResponse extends BaseResponse {

    /**
     * 账户编号
     */
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
