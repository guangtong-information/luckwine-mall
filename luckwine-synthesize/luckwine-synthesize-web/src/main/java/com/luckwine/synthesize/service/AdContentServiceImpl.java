package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.handle.local.*;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentImportReq;
import com.luckwine.synthesize.model.request.AdContentSReq;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DubboService(validation = "true")
public class AdContentServiceImpl implements AdContentService {

    @Autowired
    private AdContentPageService adContentPageService;

    @Autowired
    private AdContentAddService adContentAddService;

    @Autowired
    private AdContentAvailableListService adContentAvailableListService;

    @Autowired
    private AdContentListSService adContentListSService;

    @Autowired
    private AdContentImportService adContentImportService;

    @Autowired
    private AdContentEditService adContentEditService;

    @Autowired
    private AdContentQueryService adContentQueryService;

    @Autowired
    private AdContentBatchDeleteService adContentBatchDeleteService;

    @Autowired
    private AdContentBatchShelvesService adContentBatchShelvesService;

    @Autowired
    private AdContentBatchLowerFrameService adContentBatchLowerFrameService;


    @Override
    public CommonQueryPageResponse<List<AdContent>> queryAdContentPage(CommonQueryPageRequest<AdContent> request) {
        return adContentPageService.call(request);
    }

    @Override
    public CommonResponse<List<AdContent>> queryAdContent(CommonRequest<AdContent> request) {
        return adContentAvailableListService.call(request);
    }

    @Override
    public CommonResponse<Map<String, List<AdContent>>> queryAdContentS(CommonRequest<AdContentSReq> request) {
        return adContentListSService.call(request);
    }

    @Override
    public CommonResponse<AdContent> queryAdContentById(CommonRequest<AdContent> request) {
        return adContentQueryService.call(request);
    }

    @Override
    public CommonResponse<Boolean> insertAdContent(CommonRequest<AdContent> request) {
        return adContentAddService.call(request);
    }

    @Override
    public CommonResponse<Boolean> updateAdContent(CommonRequest<AdContent> request) {
        return adContentEditService.call(request);
    }

    @Override
    public CommonResponse<Boolean> deleteAdContent(CommonRequest<AdContent> request) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> batchDeleteAdContent(CommonRequest<List<AdContent>> request) {
            return adContentBatchDeleteService.call(request);
    }

    @Override
    public CommonResponse <Boolean> batchShelvesAdContent(CommonRequest <List <AdContent>> request) {
        return adContentBatchShelvesService.call(request);
    }

    @Override
    public CommonResponse <Boolean> batchLowerFrameAdContent(CommonRequest <List <AdContent>> request) {
        return adContentBatchLowerFrameService.call(request);
    }

    @Override
    public CommonResponse <Boolean> importAdContent(CommonRequest <AdContentImportReq> request) {
        return adContentImportService.call(request);
    }
}
