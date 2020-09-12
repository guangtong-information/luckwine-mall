package com.luckwine.portal.module.goods.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;
import com.luckwine.parent.template.EsAggRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryGoodsPageRemoteService extends EsAggRemoteTemplate<SearchSkuRequest, List<SkuDetail>> {

    @DubboReference(version = "1.0.0")
    private SkuService skuService;

    @Override
    protected EalsticsearchPageResponse<List<SkuDetail>> callRemote(CommonQueryPageRequest<SearchSkuRequest> request) throws Exception {
        return skuService.searchAggSku(request);
    }
}
