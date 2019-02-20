package com.luckwine.acct.service.trans;

import com.luckwine.acct.model.request.AcctDepositRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

/**
 * 账户入账
 */
public interface AccountDepositService {

    /**
     * 账户入账接口-充值、消费收款大账户
     *
     * @param request
     * @return 账户流水号
     */
    CommonResponse<TransRes> deposit(CommonRequest<AcctDepositRequest> request);
}
