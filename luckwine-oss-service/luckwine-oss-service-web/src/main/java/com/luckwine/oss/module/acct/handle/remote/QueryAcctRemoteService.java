package com.luckwine.oss.module.acct.handle.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.acct.service.info.AcctInfoService;
import com.luckwine.oss.acct.model.request.AcctInfoPageOssRequest;
import com.luckwine.oss.acct.model.response.AcctInfoOssResponse;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryAcctRemoteService extends QueryPageRemoteTemplate<AcctInfoPageOssRequest, List<AcctInfoOssResponse>> {

    @DubboReference(version = "1.0.0",timeout = 6000)
    private AcctInfoService acctInfoService;

    @Override
    protected CommonQueryPageResponse<List<AcctInfoOssResponse>> callRemote(CommonQueryPageRequest<AcctInfoPageOssRequest> localRequest) {
        //请求对象转换
        CommonQueryPageRequest<AcctInfoPageRequest> remoteRequest = new CommonQueryPageRequest<>();
        BeanUtils.copyProperties(localRequest, remoteRequest);
        //赋值泛型T的result
        AcctInfoPageRequest acctInfoPageRequest=new AcctInfoPageRequest();
        BeanUtils.copyProperties(localRequest.getRequest(), acctInfoPageRequest);
        remoteRequest.setRequest(acctInfoPageRequest);

        //远程请求
        CommonQueryPageResponse<List<AcctInfo>> remoteRes = acctInfoService.querylist(remoteRequest);
        //响应对象转换
        CommonQueryPageResponse<List<AcctInfoOssResponse>> localRes = new CommonQueryPageResponse<>();
        BeanUtils.copyProperties(remoteRes, localRes);
        return localRes;
    }
}
