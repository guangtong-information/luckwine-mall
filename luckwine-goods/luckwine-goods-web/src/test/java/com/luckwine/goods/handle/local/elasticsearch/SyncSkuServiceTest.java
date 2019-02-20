package com.luckwine.goods.handle.local.elasticsearch;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.handle.local.sku.SkuSyncBySpuIdService;
import com.luckwine.goods.model.request.sku.SyncSkuBySpuIdRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class SyncSkuServiceTest {

    @Autowired
    private SkuSyncBySpuIdService skuSyncBySpuIdService;

    @Test
    public void callInner() throws Exception {
        CommonRequest<SyncSkuBySpuIdRequest> commonRequest = new CommonRequest<>();
        SyncSkuBySpuIdRequest request = new SyncSkuBySpuIdRequest();
        request.setSpuId("1537352081358");
        commonRequest.setRequest(request);
        CommonResponse<Boolean> commonResponse =  skuSyncBySpuIdService.callInner(commonRequest);
        log.info("{}", commonResponse);
    }
}