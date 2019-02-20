package com.luckwine.oss.module.synthesize.remote.ad;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.service.AdContentService;
import org.springframework.stereotype.Service;

@Service
public class AdContentEditRemoteService extends SingleRemoteTemplate<AdContent, Boolean> {

    @Reference(version = "1.0.0")
    private AdContentService adContentService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<AdContent> request) {
        return adContentService.updateAdContent(request);
    }
}
