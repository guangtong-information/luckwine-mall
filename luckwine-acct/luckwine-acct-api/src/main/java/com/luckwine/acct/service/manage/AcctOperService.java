package com.luckwine.acct.service.manage;

import com.luckwine.acct.model.request.AcctFreezeBatchRequest;
import com.luckwine.acct.model.request.AcctOperRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

/**
 * 账户号管理
 */
public interface AcctOperService {

    /**
     * 账户开户
     * @param request
     * @return 返回账户号
     */
    CommonResponse<String> openAcct(CommonRequest<AcctOperRequest> request);

    /**
     * 批量冻结
     * @param request
     * @return
     */
    CommonResponse<Integer> freezeBatch(CommonRequest<AcctFreezeBatchRequest> request);

    /**
     * 批量解冻
     * @param request
     * @return
     */
    CommonResponse<Integer> unfreezeBatch(CommonRequest<AcctFreezeBatchRequest> request);
}
