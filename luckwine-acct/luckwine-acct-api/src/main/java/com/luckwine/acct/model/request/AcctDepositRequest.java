package com.luckwine.acct.model.request;

import com.luckwine.acct.model.request.base.AcctTransBaseRequest;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 账号入账
 */
@Data
@ToString(callSuper = true)
public class AcctDepositRequest extends AcctTransBaseRequest {

    /**
     * 收款账户号
     */
    @NotNull(message = "收款账户号不能为空")
    @Length(max = 32,message = "收款账户号不能超过32位长度")
    private String payeeAcctCode;


}

