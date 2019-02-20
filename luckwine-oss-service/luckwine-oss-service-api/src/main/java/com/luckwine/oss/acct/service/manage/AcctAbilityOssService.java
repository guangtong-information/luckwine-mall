package com.luckwine.oss.acct.service.manage;

import com.luckwine.oss.acct.model.request.AcctAbilityBatchOssRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

/**
 * 账户列表查询接口
 */
public interface AcctAbilityOssService {

    /**
     * 账户能力配置
     * @param request
     * @return
     */
    CommonResponse<Integer> acctAbilityBatchConfig(CommonRequest<AcctAbilityBatchOssRequest> request);

}
