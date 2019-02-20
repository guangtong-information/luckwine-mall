package com.luckwine.goods.handle.local.sku;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
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
public class SkuSearchServiceTest {

    @Autowired
    private SkuSearchService skuSearchService;


    @Test
    public void callInner() {
        CommonQueryPageRequest<SearchSkuRequest> req = new CommonQueryPageRequest<>();
        req.setPageSize(2);
        req.setPageNo(1);

        SearchSkuRequest skuRequest = new SearchSkuRequest();
         skuRequest.setText("拉菲");

        req.setRequest(skuRequest);
        CommonQueryPageResponse<List<SkuDetail>> response =  skuSearchService.callInner(req);
        log.info("{}", response);
    }
}