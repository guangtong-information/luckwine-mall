package com.luckwine.goods.handle.local.category;


import com.luckwine.goods.dao.CategoryMapper;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategoryModifyRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryModifyService extends SingleTemplate<CategoryModifyRequest,Boolean> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    protected Boolean callInner(CommonRequest<CategoryModifyRequest> request) throws Exception {
        Long categoryId=request.getRequest().getId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category!=null && !category.equals("") ){
            category.setCategoryName(request.getRequest().getCategoryName());
            category.setUpdateTime(new Date());
            categoryMapper.updateByPrimaryKey(category);
            return true;
        }
        return false;
    }
}
