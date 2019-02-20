package com.luckwine.synthesize.handle.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuDetailRemoteService extends SingleRemoteTemplate<SkuDetailGetByIdsRequest, List<SkuDetail>> {

    @Reference(version = "1.0.0", timeout = 5000)
    private SkuService skuService;

    @Override
    protected CommonResponse<List<SkuDetail>> callRemote(CommonRequest<SkuDetailGetByIdsRequest> request) {
        CommonResponse<List<SkuDetail>> remoteRes = skuService.getSkuDetail(request);
        return remoteRes;
    }
}
