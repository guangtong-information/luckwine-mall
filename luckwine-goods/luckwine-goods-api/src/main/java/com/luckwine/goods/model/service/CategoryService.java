package com.luckwine.goods.model.service;

import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategoryDeleteRequest;
import com.luckwine.goods.model.request.category.CategoryModifyRequest;
import com.luckwine.goods.model.request.category.CategoryPageRequest;
import com.luckwine.goods.model.request.category.CategorySaveRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 商品分类服务类
 */
public interface CategoryService {

    /** 新增分类 */
    CommonResponse<Boolean> saveCategory(CommonRequest<CategorySaveRequest> request);

    /** 修改分类 */
    CommonResponse<Boolean> modifyCategory(CommonRequest<CategoryModifyRequest> request);

    /** 删除分类 */
    CommonResponse<Boolean> deleteCategory(CommonRequest<CategoryDeleteRequest> request);

    /** 分页查询分类 */
    CommonQueryPageResponse<List<Category>> page(CommonQueryPageRequest<CategoryPageRequest> request);
    
}
