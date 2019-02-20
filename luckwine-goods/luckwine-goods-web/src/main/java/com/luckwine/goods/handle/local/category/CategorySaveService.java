package com.luckwine.goods.handle.local.category;

import com.luckwine.goods.dao.CategoryMapper;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategorySaveRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategorySaveService extends SingleTemplate<CategorySaveRequest, Boolean> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    protected Boolean callInner(CommonRequest<CategorySaveRequest> request) throws Exception {
        Category category = new Category();
        category.setCategoryName(request.getRequest().getCategoryName());
        category.setCreateTime(new Date());
        int count = categoryMapper.insertSelective(category);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
