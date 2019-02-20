package com.luckwine.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.goods.handle.local.category.CategoryDeleteService;
import com.luckwine.goods.handle.local.category.CategoryModifyService;
import com.luckwine.goods.handle.local.category.CategoryPageService;
import com.luckwine.goods.handle.local.category.CategorySaveService;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategoryDeleteRequest;
import com.luckwine.goods.model.request.category.CategoryModifyRequest;
import com.luckwine.goods.model.request.category.CategoryPageRequest;
import com.luckwine.goods.model.request.category.CategorySaveRequest;
import com.luckwine.goods.model.service.CategoryService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryPageService categoryPageService;

    @Autowired
    private CategorySaveService categorySaveService;

    @Autowired
    private CategoryDeleteService categoryDeleteService;

    @Autowired
    private CategoryModifyService categoryModifyService;

    @Override
    public CommonResponse<Boolean> saveCategory(CommonRequest<CategorySaveRequest> request) {
        return categorySaveService.call(request);
    }

    @Override
    public CommonResponse<Boolean> modifyCategory(CommonRequest<CategoryModifyRequest> request) {

        return categoryModifyService.call(request);
    }

    @Override
    public CommonResponse<Boolean> deleteCategory(CommonRequest<CategoryDeleteRequest> request) {

        return categoryDeleteService.call(request);
    }

    @Override
    public CommonQueryPageResponse<List<Category>> page(CommonQueryPageRequest<CategoryPageRequest> request) {
        return categoryPageService.call(request);
    }
}
