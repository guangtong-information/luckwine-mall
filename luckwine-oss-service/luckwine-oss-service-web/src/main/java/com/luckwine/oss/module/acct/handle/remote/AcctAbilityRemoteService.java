package com.luckwine.oss.module.acct.handle.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.acct.model.request.AcctAbilityBatchRequest;
import com.luckwine.acct.service.manage.AcctAbilityService;
import com.luckwine.oss.acct.model.request.AcctAbilityBatchOssRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AcctAbilityRemoteService extends SingleRemoteTemplate<AcctAbilityBatchOssRequest, Integer> {

    @Reference(version = "1.0.0", timeout = 2000)
    private AcctAbilityService acctAbilityService;

    @Override
    protected CommonResponse<Integer> callRemote(CommonRequest<AcctAbilityBatchOssRequest> localRequest) {
        //请求对象转换
        CommonRequest<AcctAbilityBatchRequest> remoteRequest = new CommonRequest<>();
        BeanUtils.copyProperties(localRequest, remoteRequest);
        //赋值泛型T的result
        AcctAbilityBatchRequest acctAbilityBatchRequest = new AcctAbilityBatchRequest();
        BeanUtils.copyProperties(localRequest.getRequest(), acctAbilityBatchRequest);
        remoteRequest.setRequest(acctAbilityBatchRequest);

        //远程请求
        CommonResponse<Integer> remoteRes = acctAbilityService.acctAbilityBatchConfig(remoteRequest);


        return remoteRes;
    }
}
