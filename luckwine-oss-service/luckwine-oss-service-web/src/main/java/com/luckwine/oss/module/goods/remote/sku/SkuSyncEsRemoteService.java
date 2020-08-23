package com.luckwine.oss.module.goods.remote.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.sku.SyncSkuBySpuIdRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class SkuSyncEsRemoteService extends SingleRemoteTemplate<SyncSkuBySpuIdRequest, Boolean> {


    @DubboReference(version = "1.0.0")
    private SkuService skuService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<SyncSkuBySpuIdRequest> request) {
        return skuService.syncSkuBySpuId(request);
    }
}
