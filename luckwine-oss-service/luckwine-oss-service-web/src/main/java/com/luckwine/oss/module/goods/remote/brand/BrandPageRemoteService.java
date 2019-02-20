package com.luckwine.oss.module.goods.remote.brand;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.request.brand.BrandPageRequest;
import com.luckwine.goods.model.service.BrandService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandPageRemoteService extends QueryPageRemoteTemplate<BrandPageRequest, List<Brand>> {

    @Reference(version = "1.0.0")
    private BrandService brandService;

    @Override
    protected CommonQueryPageResponse<List<Brand>> callRemote(CommonQueryPageRequest<BrandPageRequest> request) throws Exception {
        return brandService.page(request);
    }
}
