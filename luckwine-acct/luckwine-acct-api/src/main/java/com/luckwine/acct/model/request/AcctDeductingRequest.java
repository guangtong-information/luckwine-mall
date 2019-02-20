package com.luckwine.acct.model.request;

import com.luckwine.acct.enums.AbilityCode;
import com.luckwine.acct.model.request.base.AcctTransBaseRequest;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 账户出账
 */
@Data
@ToString(callSuper = true)
public class AcctDeductingRequest extends AcctTransBaseRequest {

    /**
     * 付款账户号
     */
    @NotNull(message = "付款账户号不能为空")
    @Length(max = 32,message = "付款账户号不能超过32位长度")
    private String payerAcctCode;

    private AbilityCode abilityCode;
}

