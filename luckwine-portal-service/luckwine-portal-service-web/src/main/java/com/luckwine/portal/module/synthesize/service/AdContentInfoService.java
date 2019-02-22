package com.luckwine.portal.module.synthesize.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.portal.module.synthesize.remote.QueryAdContentSRemoteService;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentSReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdContentInfoService {
    @Autowired
    private QueryAdContentSRemoteService queryAdContentSRemoteService;

    @Cacheable(value = "portal",  key = "'adContent::'.concat(#pageId.toString())")
    public Map<String,List<AdContent>> getAdContent(CommonRequest<AdContentSReq> request, String pageId) {
        AdContentSReq req = request.getRequest();
        req.setPageId(pageId);
        request.setRequest(req);
        CommonResponse<Map<String,List<AdContent>>> commonResponse = queryAdContentSRemoteService.call(request);
        return commonResponse.getResponse();
    }

    @CacheEvict(value = "portal", key = "'adContent::'.concat(#pageId.toString())")
    public String delCache(String pageId) {
        return "ok";
    }

}
