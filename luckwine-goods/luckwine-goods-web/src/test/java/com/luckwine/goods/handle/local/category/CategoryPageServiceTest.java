package com.luckwine.goods.handle.local.category;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.request.category.CategoryPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class CategoryPageServiceTest {
    @Autowired
    private CategoryPageService categoryPageService;

    @Test
    public void test() throws Exception {
        CommonQueryPageRequest<CategoryPageRequest> request = new CommonQueryPageRequest<>();
        request.setPageNo(1);
        request.setPageSize(5);
        CategoryPageRequest category = new CategoryPageRequest();
        category.setCategoryName("酒");
        request.setRequest(category);
        List<Category> categoryList = categoryPageService.callInner(request);
        log.info("{}", categoryList);
        category.setCategoryName("白");
        request.setRequest(category);
        categoryList = categoryPageService.callInner(request);
        log.info("{}", categoryList);
    }
}