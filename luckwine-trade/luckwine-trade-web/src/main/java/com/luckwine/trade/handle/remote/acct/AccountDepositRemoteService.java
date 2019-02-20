package com.luckwine.trade.handle.remote.acct;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.acct.model.request.AcctDepositRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.acct.service.trans.AccountDepositService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

/**
 * 余额入账
 */
@Service
public class AccountDepositRemoteService extends SingleRemoteTemplate<AcctDepositRequest, TransRes> {

    @Reference(version = "1.0.0", timeout = 25000)
    private AccountDepositService accountDepositService;

    @Override
    protected CommonResponse<TransRes> callRemote(CommonRequest<AcctDepositRequest> remoteRequest) {
        //远程请求
        CommonResponse<TransRes> remoteRes = accountDepositService.deposit(remoteRequest);
        return remoteRes;
    }
}
