package com.luckwine.acct.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 账户列表请求参数
 */
@Data
@ToString(callSuper = true)
public class AcctAbilityBatchRequest extends BaseRequest {
    /**
     * 账户号列表
     */
    @Size(min = 1,message = "操作的账户号不能小于1")
    private List<String> acctCodeList;

    /**
     * 能力编码列表
     */
    @Size(min = 1,message = "操作的账户能力不能小于1")
    private List<String> abilityCodeList;
}

