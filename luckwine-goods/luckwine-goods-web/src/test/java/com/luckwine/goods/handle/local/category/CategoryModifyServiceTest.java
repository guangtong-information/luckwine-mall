package com.luckwine.goods.handle.local.category;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.request.category.CategoryModifyRequest;
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
public class CategoryModifyServiceTest {
    @Autowired
    private CategoryModifyService categoryModifyService;
    @Test
    public void test() throws Exception {
        CommonRequest<CategoryModifyRequest> request = new CommonRequest<>();
        CategoryModifyRequest category = new CategoryModifyRequest();
        Long l=new Long(66);
        category.setId(l);
        category.setCategoryName("测试2");
        request.setRequest(category);
        Boolean terminated = categoryModifyService.callInner(request);
        //log.info("{}，{}",terminated,"sss");
       // Assert.assertEquals(1,1);
//        category.setCatego, ryName("白酒");
//        request.setRequest(category);
//        terminated=categoryModifyService.callInner(request);
//        log.info("{}",terminated);
    }
}
