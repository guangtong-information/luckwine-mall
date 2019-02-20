package com.luckwine.oss.acct.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 账户列表请求参数
 */
@Data
@ToString(callSuper = true)
public class AcctAbilityBatchOssRequest extends BaseRequest {
    /**
     * 账户号列表
     */
    private List<String> acctCodeList;

    /**
     * 能力编码列表
     */
    private List<String> abilityCodeList;
}

