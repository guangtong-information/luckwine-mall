package com.luckwine.oss.module.goods.remote.brand;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.brand.BrandSaveRequest;
import com.luckwine.goods.model.service.BrandService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class BrandSaveRemoteService extends SingleRemoteTemplate<BrandSaveRequest, Boolean> {

    @Reference(version = "1.0.0")
    private BrandService brandService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<BrandSaveRequest> request) {
        return brandService.saveBrand(request);
    }
}
