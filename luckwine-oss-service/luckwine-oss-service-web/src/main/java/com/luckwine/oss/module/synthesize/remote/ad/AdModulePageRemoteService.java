package com.luckwine.oss.module.synthesize.remote.ad;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import com.luckwine.synthesize.model.base.AdModule;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.service.AdModuleService;
import com.luckwine.synthesize.service.SmsLogService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdModulePageRemoteService extends QueryPageRemoteTemplate<AdModule, List<AdModule>> {

    @DubboReference(validation = "true")
    private AdModuleService adModuleService;

    @Override
    protected CommonQueryPageResponse<List<AdModule>> callRemote(CommonQueryPageRequest<AdModule> request) throws Exception {
        return adModuleService.queryAdModulePage(request);
    }
}
