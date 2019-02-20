package com.luckwine.goods.handle.local.category;

import com.luckwine.goods.dao.CategoryMapper;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategoryPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryPageService extends QueryPageTemplate<CategoryPageRequest, Category> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    protected List<Category> callInner(CommonQueryPageRequest<CategoryPageRequest> request) throws Exception {
        Example example = new Example(Category.class);
        if (StringUtils.isNotBlank(request.getRequest().getCategoryName())) {
            example.createCriteria().andLike( "categoryName","%" + request.getRequest().getCategoryName() + "%");
        }
        return categoryMapper.selectByExample(example);
    }
}
