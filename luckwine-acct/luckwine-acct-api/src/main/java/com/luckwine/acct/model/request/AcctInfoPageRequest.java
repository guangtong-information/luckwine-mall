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
public class AcctInfoPageRequest extends BaseRequest {
    /**
     * 余额账户号
     */
    @Pattern(regexp = "^[\\w]*$", message = "账号不能包含特殊字符")
    private String acctCode;

    /**
     * 余额账户名称
     */
    private String acctName;

    /**
     * 客户登录账号,关联cust_info表
     */
    private String loginAccount;

    /**
     * 账户状态
     */
    @Pattern(regexp = "[0-9]{2}", message = "状态只有两个数字")
    private String stat;

    /**
     * 开户开始时间
     */
    private String createTimeStart;

    /**
     * 开户结束时间
     */
    private String createTimeEnd;

    /**
     * 账户能力
     */
    @Pattern(regexp = "[0-9]{4}", message = "状态只有四个数字")
    private String abilityCode;
}

