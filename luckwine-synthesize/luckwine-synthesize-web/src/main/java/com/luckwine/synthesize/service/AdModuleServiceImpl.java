package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.synthesize.handle.local.AdModulePageService;
import com.luckwine.synthesize.model.base.AdModule;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(validation = "true")
public class AdModuleServiceImpl implements AdModuleService {

    @Autowired
    private AdModulePageService adModulePageService;

    @Override
    public CommonQueryPageResponse<List<AdModule>> queryAdModulePage(CommonQueryPageRequest<AdModule> request) {
        return adModulePageService.call(request);
    }
}
