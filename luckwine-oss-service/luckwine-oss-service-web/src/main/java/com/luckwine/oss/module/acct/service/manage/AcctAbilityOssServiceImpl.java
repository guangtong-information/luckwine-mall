package com.luckwine.oss.module.acct.service.manage;

import com.luckwine.oss.acct.model.request.AcctAbilityBatchOssRequest;
import com.luckwine.oss.acct.service.manage.AcctAbilityOssService;
import com.luckwine.oss.module.acct.handle.remote.AcctAbilityRemoteService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AcctAbilityOssServiceImpl implements AcctAbilityOssService {

    @Resource
    private AcctAbilityRemoteService acctAbilityBatchConfigService;

    @Override
    public CommonResponse<Integer> acctAbilityBatchConfig(CommonRequest<AcctAbilityBatchOssRequest> request){
        return acctAbilityBatchConfigService.call(request);
    }
}
