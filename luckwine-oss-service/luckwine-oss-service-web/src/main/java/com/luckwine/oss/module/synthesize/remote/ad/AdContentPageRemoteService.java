package com.luckwine.oss.module.synthesize.remote.ad;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.base.AdModule;
import com.luckwine.synthesize.service.AdContentService;
import com.luckwine.synthesize.service.AdModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdContentPageRemoteService extends QueryPageRemoteTemplate<AdContent, List<AdContent>> {

    @Reference(validation = "true")
    private AdContentService adContentService;

    @Override
    protected CommonQueryPageResponse<List<AdContent>> callRemote(CommonQueryPageRequest<AdContent> request) throws Exception {
        return adContentService.queryAdContentPage(request);
    }
}
