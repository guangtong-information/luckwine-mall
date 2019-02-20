package com.luckwine.acct.service.manage;

import com.luckwine.acct.model.request.AcctAbilityBatchRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

/**
 * 账户能力配置
 */
public interface AcctAbilityService {

    /**
     * 批量账户能力配置
     * @param request
     * @return
     */
    CommonResponse<Integer> acctAbilityBatchConfig(CommonRequest<AcctAbilityBatchRequest> request);

}
