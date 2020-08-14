package com.luckwine.trade.handle.remote.acct;

import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.acct.service.info.AcctInfoService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户查询
 */
@Service
public class AccountInfoRemoteService extends QueryPageRemoteTemplate<AcctInfoPageRequest, List<AcctInfo>> {

    @DubboReference(version = "1.0.0", timeout = 25000)
    private AcctInfoService acctInfoService;

    @Override
    protected CommonQueryPageResponse<List<AcctInfo>> callRemote(CommonQueryPageRequest<AcctInfoPageRequest> remoteRequest) {
        //远程请求
        CommonQueryPageResponse<List<AcctInfo>> remoteRes = acctInfoService.querylist(remoteRequest);
        return remoteRes;
    }
}
