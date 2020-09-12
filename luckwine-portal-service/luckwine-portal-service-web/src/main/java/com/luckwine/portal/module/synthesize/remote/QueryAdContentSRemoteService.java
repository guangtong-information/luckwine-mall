package com.luckwine.portal.module.synthesize.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentSReq;
import com.luckwine.synthesize.service.AdContentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryAdContentSRemoteService extends SingleRemoteTemplate<AdContentSReq, Map<String,List<AdContent>>> {

    @DubboReference(version = "1.0.0")
    private AdContentService adContentService;

    @Override
    protected CommonResponse<Map<String,List<AdContent>>> callRemote(CommonRequest<AdContentSReq> request) {
        return adContentService.queryAdContentS(request);
    }
}
