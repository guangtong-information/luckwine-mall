package com.luckwine.acct.service.trans;

import com.luckwine.acct.model.request.AcctDeductingRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

/**
 * 账户出账
 */
public interface AccountDeductingService {

    /**
     * 账户出账接口-提现
     *
     * @param request
     * @return 账户流水号
     */
    CommonResponse<TransRes> withdraw(CommonRequest<AcctDeductingRequest> request);

    /**
     * 账户出账接口-消费
     *
     * @param request
     * @return 账户流水号
     */
    CommonResponse<TransRes> consume(CommonRequest<AcctDeductingRequest> request);
}
