package com.luckwine.marketing.handle.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonRequest;

import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SkuListBySkuIdService  extends SingleRemoteTemplate<SkuDetailGetByIdsRequest, List<SkuDetail>> {

    @Reference//(version = "1.0.0", timeout = 5000)
    private SkuService skuService;

    /**
     * 根据skuIds获取skuList
     * @param remoteRequest
     * @return
     * @throws Exception
     */
    @Override
    protected CommonResponse<List<SkuDetail>> callRemote(CommonRequest<SkuDetailGetByIdsRequest> remoteRequest) {
        //远程请求
        CommonResponse<List<SkuDetail>> remoteRes = skuService.getSkuDetail(remoteRequest);
        return remoteRes;
    }

}
