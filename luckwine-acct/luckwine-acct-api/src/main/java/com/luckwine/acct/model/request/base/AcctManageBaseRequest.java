package com.luckwine.acct.model.request.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 * 账户操作基础类
 */
@Data
@ToString(callSuper = true)
public class AcctManageBaseRequest extends BaseRequest {

    /**
     * 余额账户号
     */
    @NotNull(message = "账号不能为空")
    @Pattern(regexp = "^[\\w]*$", message = "账号不能包含特殊字符")
    private String acctCode;

}

