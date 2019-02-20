package com.luckwine.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.goods.handle.local.sku.SkuDetailGetByIdsService;
import com.luckwine.goods.handle.local.sku.SkuSearchAggService;
import com.luckwine.goods.handle.local.sku.SkuSearchService;
import com.luckwine.goods.handle.local.sku.SkuSyncBySpuIdService;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.*;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuSyncBySpuIdService skuSyncBySpuIdService;

    @Autowired
    private SkuSearchService skuSearchService;

    @Autowired
    private SkuSearchAggService skuSearchAggService;

    @Autowired
    private SkuDetailGetByIdsService skuDetailGetByIdsService;

    @Override
    public CommonResponse<Boolean> statusModify(CommonRequest<SkuStatusModifyRequest> request) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> stockModify(CommonRequest<SkuStockModifyRequest> request) {
        //TODO 临时写死
        CommonResponse<Boolean> commonResponse = new CommonResponse<>();
        commonResponse.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        commonResponse.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
        commonResponse.setResponse(true);
        return commonResponse;
    }

    @Override
    public CommonResponse<List<SkuDetail>> getSkuDetail(CommonRequest<SkuDetailGetByIdsRequest> request) {
        return skuDetailGetByIdsService.call(request);
    }

    @Override
    public CommonResponse<Boolean> syncSkuBySpuId(CommonRequest<SyncSkuBySpuIdRequest> request) {
        return skuSyncBySpuIdService.callInner(request);
    }

    @Override
    public CommonQueryPageResponse searchSku(CommonQueryPageRequest<SearchSkuRequest> request) {
        return skuSearchService.callInner(request);
    }

    @Override
    public EalsticsearchPageResponse<List<SkuDetail>> searchAggSku(CommonQueryPageRequest<SearchSkuRequest> request) {
        return skuSearchAggService.callInner(request);
    }
}
