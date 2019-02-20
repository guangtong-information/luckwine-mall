package com.luckwine.oss.module.goods.remote.category;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategoryPageRequest;
import com.luckwine.goods.model.service.CategoryService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryPageRemoteService extends QueryPageRemoteTemplate<CategoryPageRequest, List<Category>> {

    @Reference(version = "1.0.0")
    private CategoryService categoryService;

    @Override
    protected CommonQueryPageResponse<List<Category>> callRemote(CommonQueryPageRequest<CategoryPageRequest> request) throws Exception {
        return categoryService.page(request);
    }
}
