package com.luckwine.goods.model.service;

import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.*;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;

import java.util.List;

public interface SkuService {

    /*  状态变更 */
    CommonResponse<Boolean> statusModify(CommonRequest<SkuStatusModifyRequest> request);

    /* 库存变更 */
    CommonResponse<Boolean> stockModify(CommonRequest<SkuStockModifyRequest> request);

    /* 获取sku详情 */
    CommonResponse<List<SkuDetail>> getSkuDetail(CommonRequest<SkuDetailGetByIdsRequest> request);

    /* 根据spuId同步到es  */
    CommonResponse<Boolean> syncSkuBySpuId(CommonRequest<SyncSkuBySpuIdRequest> request);

    /** 搜索sku */
    CommonQueryPageResponse<List<SkuDetail>> searchSku(CommonQueryPageRequest<SearchSkuRequest> request);

    /* 聚合搜索sku */
    EalsticsearchPageResponse<List<SkuDetail>> searchAggSku(CommonQueryPageRequest<SearchSkuRequest> request);
}
