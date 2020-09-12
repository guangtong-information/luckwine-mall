package com.luckwine.portal.module.goods.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.spu.SpuDetailRequest;
import com.luckwine.goods.model.response.SpuDetail;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class QueryGoodsDetailRemoteService extends SingleRemoteTemplate<SpuDetailRequest, SpuDetail> {
    @DubboReference(version = "1.0.0")
    private SpuService spuService;

    @Override
    protected CommonResponse<SpuDetail> callRemote(CommonRequest<SpuDetailRequest> request) {
        return spuService.detailSpu(request);
    }
}
