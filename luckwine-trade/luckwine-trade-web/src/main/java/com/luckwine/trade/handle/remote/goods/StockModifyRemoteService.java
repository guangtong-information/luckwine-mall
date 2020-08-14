package com.luckwine.trade.handle.remote.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.sku.SkuStockModifyRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class StockModifyRemoteService extends SingleRemoteTemplate<SkuStockModifyRequest, Boolean> {

    @DubboReference(version = "1.0.0", timeout = 25000)
    private SkuService skuService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<SkuStockModifyRequest> remoteRequest) {
        //远程请求
        CommonResponse<Boolean> remoteRes = skuService.stockModify(remoteRequest);
        return remoteRes;
    }
}
