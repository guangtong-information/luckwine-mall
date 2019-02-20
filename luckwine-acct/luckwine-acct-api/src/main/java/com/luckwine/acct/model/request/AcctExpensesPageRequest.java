package com.luckwine.acct.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;

/**
 * 账户列表请求参数
 */
@Data
@ToString(callSuper = true)
public class AcctExpensesPageRequest extends BaseRequest {
    /**
     * 账户流水号
     */
    @Pattern(regexp = "^[\\w]*$", message = "账户流水号不能包含特殊字符")
    private String acctOrderno;

    /**
     * 关联账户号
     */
    @Pattern(regexp = "^[\\w]*$", message = "账户号不能包含特殊字符")
    private String acctCode;

    /**
     * 外部系统订单号(订单模块的主单号)
     */
    private String extTrsSeq;

    /**
     * 交易类型:充值、提现、消费
     */
    private String transType;

    /**
     * 收支类型
     */
    private String expenseType;

    /**
     * 交易开始时间
     */
    private String createTimeStart;

    /**
     * 交易结束时间
     */
    private String createTimeEnd;

}

