package com.luckwine.goods.model.service;

import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.request.brand.BrandDeleteRequest;
import com.luckwine.goods.model.request.brand.BrandModifyRequest;
import com.luckwine.goods.model.request.brand.BrandPageRequest;
import com.luckwine.goods.model.request.brand.BrandSaveRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 *  商品品牌服务类.
 */
public interface BrandService {

    /** 添加品牌 */
    CommonResponse<Boolean> saveBrand(CommonRequest<BrandSaveRequest> request);

    /** 修改品牌 */
    CommonResponse<Boolean> modifyBrand(CommonRequest<BrandModifyRequest> request);

    /** 删除品牌 */
    CommonResponse<Boolean> deleteBrand(CommonRequest<BrandDeleteRequest> request);

    /** 分页查询品牌 */
    CommonQueryPageResponse<List<Brand>> page(CommonQueryPageRequest<BrandPageRequest> request);

}
