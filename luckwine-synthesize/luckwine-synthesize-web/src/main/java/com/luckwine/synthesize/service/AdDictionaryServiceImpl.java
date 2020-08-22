package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.handle.local.adDictionary.AdDictionaryListService;
import com.luckwine.synthesize.model.base.AdDictionary;
import com.luckwine.synthesize.model.request.AdDictionaryRequest;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(validation = "true")
public class AdDictionaryServiceImpl implements  AdDictionaryService {

    @Autowired
    private AdDictionaryListService adDictionaryListService;

    @Override
    public CommonResponse<List<AdDictionary>> queryAllSystem() {
        CommonRequest<AdDictionaryRequest> request = new CommonRequest<>();
        AdDictionaryRequest adDictionaryRequest = new AdDictionaryRequest();
        adDictionaryRequest.setParentId("");
        request.setRequest(adDictionaryRequest);
        return adDictionaryListService.call(request);
    }

    @Override
    public CommonResponse<List<AdDictionary>> queryAllPage() {
        CommonRequest<AdDictionaryRequest> request = new CommonRequest<>();
        AdDictionaryRequest adDictionaryRequest = new AdDictionaryRequest();
        adDictionaryRequest.setParentId("1");
        request.setRequest(adDictionaryRequest);
        return adDictionaryListService.call(request);
    }

    @Override
    public CommonResponse<List<AdDictionary>> queryPagesBySystemId(CommonRequest<String> request) {
        CommonRequest<AdDictionaryRequest> param = new CommonRequest<>();
        AdDictionaryRequest adDictionaryRequest = new AdDictionaryRequest();
        adDictionaryRequest.setParentId(request.getRequest());
        param.setRequest(adDictionaryRequest);
        return adDictionaryListService.call(param);
    }
}
