package com.luckwine.trade.handle.remote.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSkuDetailRemoteService extends SingleRemoteTemplate<SkuDetailGetByIdsRequest, List<SkuDetail>> {

    @DubboReference(version = "1.0.0", timeout = 25000)
    private SkuService skuService;

    @Override
    protected CommonResponse<List<SkuDetail>> callRemote(CommonRequest<SkuDetailGetByIdsRequest> remoteRequest) {
        //远程请求
        CommonResponse<List<SkuDetail>> remoteRes = skuService.getSkuDetail(remoteRequest);
        return remoteRes;
    }
}
