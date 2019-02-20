package com.luckwine.synthesize.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.synthesize.handle.local.AdModulePageService;
import com.luckwine.synthesize.model.base.AdModule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(validation = "true")
public class AdModuleServiceImpl implements AdModuleService {

    @Autowired
    private AdModulePageService adModulePageService;

    @Override
    public CommonQueryPageResponse<List<AdModule>> queryAdModulePage(CommonQueryPageRequest<AdModule> request) {
        return adModulePageService.call(request);
    }
}
