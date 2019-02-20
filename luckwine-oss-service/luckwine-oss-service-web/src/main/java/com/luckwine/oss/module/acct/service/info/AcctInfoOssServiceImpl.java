package com.luckwine.oss.module.acct.service.info;

import com.luckwine.oss.acct.model.request.AcctInfoPageOssRequest;
import com.luckwine.oss.acct.model.response.AcctInfoOssResponse;
import com.luckwine.oss.acct.service.info.AcctInfoOssService;
import com.luckwine.oss.module.acct.handle.remote.QueryAcctRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AcctInfoOssServiceImpl implements AcctInfoOssService {

    @Resource
    private QueryAcctRemoteService queryAcctRemoteService;

    @Override
    public CommonQueryPageResponse<List<AcctInfoOssResponse>> query(CommonQueryPageRequest<AcctInfoPageOssRequest> acctInfoPageRequest) {
        return queryAcctRemoteService.call(acctInfoPageRequest);
    }
}
