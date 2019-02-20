package com.luckwine.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.goods.handle.local.brand.BrandPageService;
import com.luckwine.goods.handle.local.brand.BrandSaveService;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.request.brand.BrandDeleteRequest;
import com.luckwine.goods.model.request.brand.BrandModifyRequest;
import com.luckwine.goods.model.request.brand.BrandPageRequest;
import com.luckwine.goods.model.request.brand.BrandSaveRequest;
import com.luckwine.goods.model.service.BrandService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  dubbo debug
 *  invoke BrandService.saveBrand({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.brand.BrandSaveRequest","brandName":"aa"}})
 *
 * */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandSaveService brandSaveService;

    @Autowired
    private BrandPageService brandPageService;

    @Override
    public CommonResponse<Boolean> saveBrand(CommonRequest<BrandSaveRequest> request) {
        return brandSaveService.call(request);
    }

    @Override
    public CommonResponse<Boolean> modifyBrand(CommonRequest<BrandModifyRequest> request) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> deleteBrand(CommonRequest<BrandDeleteRequest> request) {
        return null;
    }

    @Override
    public CommonQueryPageResponse<List<Brand>> page(CommonQueryPageRequest<BrandPageRequest> request) {
        return brandPageService.call(request);
    }
}
