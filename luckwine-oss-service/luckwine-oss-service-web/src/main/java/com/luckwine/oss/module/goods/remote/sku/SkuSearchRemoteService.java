package com.luckwine.oss.module.goods.remote.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuSearchRemoteService extends QueryPageRemoteTemplate<SearchSkuRequest, List<SkuDetail>> {

    @Reference(version = "1.0.0")
    private SkuService skuService;

    @Override
    protected CommonQueryPageResponse<List<SkuDetail>> callRemote(CommonQueryPageRequest<SearchSkuRequest> request) throws Exception {
        return skuService.searchSku(request);
    }
}
