package com.luckwine.oss.module.synthesize.remote.ad;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import com.luckwine.synthesize.model.request.AdContentImportReq;
import com.luckwine.synthesize.service.AdContentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class AdContentImportRemoteService extends SingleRemoteTemplate<AdContentImportReq, Boolean> {

    @DubboReference(version = "1.0.0", timeout = 6000)
    private AdContentService adContentService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<AdContentImportReq> request) {
        return adContentService.importAdContent(request);
    }
}
