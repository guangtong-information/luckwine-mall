package com.luckwine.goods.handle.local.sku;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;
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
public class SkuSearchAggServiceTest {

    @Autowired
    private SkuSearchAggService skuSearchAggService;

    @Test
    public void test() {

        CommonQueryPageRequest<SearchSkuRequest> req = new CommonQueryPageRequest<>();
        req.setPageSize(2);
        req.setPageNo(1);

        SearchSkuRequest skuRequest = new SearchSkuRequest();
        skuRequest.setText("水井坊往事");
//        skuRequest.setBrandId(4208L);
//        skuRequest.setCategoryId(49L);

        req.setRequest(skuRequest);
        EalsticsearchPageResponse<List<SkuDetail>> response =  skuSearchAggService.callInner(req);
        log.info("{}", response);

    }

}