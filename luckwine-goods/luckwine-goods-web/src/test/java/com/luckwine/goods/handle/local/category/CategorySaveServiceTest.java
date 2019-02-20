package com.luckwine.goods.handle.local.category;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.request.category.CategorySaveRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class CategorySaveServiceTest {
    @Autowired
    private CategorySaveService categorySaveService;
    @Test()
    public void test() throws Exception {
        CommonRequest<CategorySaveRequest> request = new CommonRequest<>();
        CategorySaveRequest category = new CategorySaveRequest();
        category.setCategoryName("测试");
        request.setRequest(category);

//        CommonRequest<CategorySaveRequest> request1 = new CommonRequest<>();
//        category.setCategoryName("");
//        request1.setRequest(category);
//
//        CommonRequest<CategorySaveRequest> request2 = new CommonRequest<>();
//        category.setCategoryName(null);
//        request2.setRequest(category);

//        CommonRequest<CategorySaveRequest> request3 = new CommonRequest<>();
//        category.setCategoryName("白酒");
//        request3.setRequest(category);

        Boolean terminated = categorySaveService.callInner(request);
        log.info("{}",terminated);
    }
}
