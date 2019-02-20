package com.luckwine.goods.handle.local.category;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.goods.model.request.category.CategoryDeleteRequest;
import com.luckwine.goods.model.request.spu.SkuVO;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.goods.model.service.CategoryService;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class CategoryDeleteServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuService spuService;


    @Test
    public void testDelect(){
        CommonRequest<CategoryDeleteRequest> request = new CommonRequest<>();
        Long l=new Long(65);
        CategoryDeleteRequest category=new CategoryDeleteRequest();
        category.setId(l);
        request.setRequest(category);
        CommonResponse<Boolean> response = categoryService.deleteCategory(request);
        log.info("{}",response);
    }
    @Test
    public  void  testDelectWithSpu(){
        //创建分类删除请求和spu保存请求
        CommonRequest<CategoryDeleteRequest> deleteCategoryRequest = new CommonRequest<>();
        CommonRequest<SpuSaveRequest> saveSpuRequest = new CommonRequest<>();
        //设置spu保存请求对象
        SpuSaveRequest spuRequest = new SpuSaveRequest();
        spuRequest.setCategoryId(new Long(67));
        spuRequest.setBrandId(new Long(4208));
        spuRequest.setSpuStatus(SpuStatus.IN_STOCK);
        spuRequest.setGoodsName("分类删除测试");
        List<SkuVO> skuList = new ArrayList<SkuVO>();
        SkuVO sku = new SkuVO();
        sku.setSkuName("sku测试");
        sku.setPrice(new BigDecimal(1));
        sku.setProps("27:617");
        sku.setQuantity(new Long(1000));
        skuList.add(sku);
        spuRequest.setSkus(skuList);
        spuRequest.setProps("33:632");
        saveSpuRequest.setRequest(spuRequest);
        CommonResponse<Boolean> skuSaveResponse = spuService.saveSpu(saveSpuRequest);
        log.info("{}",skuSaveResponse);
        //设置分类删除请求对象
        CategoryDeleteRequest categoryRequest = new CategoryDeleteRequest();
        categoryRequest.setId(new Long(67));
        deleteCategoryRequest.setRequest(categoryRequest);
        CommonResponse<Boolean> categoryDeleteResponse = categoryService.deleteCategory(deleteCategoryRequest);
        log.info("{}",categoryDeleteResponse);
    }

}
