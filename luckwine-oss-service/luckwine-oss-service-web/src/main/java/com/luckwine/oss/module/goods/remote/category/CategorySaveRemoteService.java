package com.luckwine.oss.module.goods.remote.category;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.category.CategorySaveRequest;
import com.luckwine.goods.model.service.CategoryService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class CategorySaveRemoteService extends SingleRemoteTemplate<CategorySaveRequest, Boolean> {

    @DubboReference(version = "1.0.0")
    private CategoryService categoryService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<CategorySaveRequest> request) {
        return categoryService.saveCategory(request);
    }
}
