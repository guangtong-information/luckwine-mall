package com.luckwine.trade.service.test.createOrder;

import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.handle.remote.goods.GetSkuDetailRemoteService;
import com.luckwine.trade.handle.remote.goods.StockModifyRemoteService;
import com.luckwine.trade.model.request.ConsumeOrderRequest;
import com.luckwine.trade.model.request.GoodsOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.service.test.base.BaseTest;
import com.luckwine.trade.service.test.base.MockResHelp;
import com.luckwine.trade.service.trans.CreateOrderService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品查询异常测试
 */
public class QueryGoodsFailTest extends BaseTest {


    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    @InjectMocks
    private StockModifyRemoteService stockModifyRemoteService;

    @Mock
    private SkuService skuService;

    @Autowired
    @InjectMocks
    private GetSkuDetailRemoteService getSkuDetailRemoteService;


    /**
     * 下单服务：查询商品模块异常
     */
    @Test
    public void testCreateOrderQueryGoodsFail() {
        //查看异常
        Mockito.doReturn(MockResHelp.skuDetailFail()).when(skuService).getSkuDetail(Mockito.any(CommonRequest.class));
        //下单工作流
        CommonResponse<CreateOrderRes> res = createOrderService.consumeCreateOrder(BaseTest.request);
        System.out.println("下单结果：" + res);
    }


    /**
     * 通用请求参数
     */
    @Override
    public void initRequestInner() {
        //购买的商品列表
        List<GoodsOrderRequest> orderGoodsList = new ArrayList<GoodsOrderRequest>();

        //商品1
        GoodsOrderRequest GoodsOrderRequest1 = new GoodsOrderRequest();
        GoodsOrderRequest1.setSkuId("10000093");
        GoodsOrderRequest1.setCartId("1");
        GoodsOrderRequest1.setGoodsCount(2L);
        orderGoodsList.add(GoodsOrderRequest1);
        //商品2
        GoodsOrderRequest GoodsOrderRequest2 = new GoodsOrderRequest();
        GoodsOrderRequest2.setSkuId("10000094");
        GoodsOrderRequest2.setCartId("2");
        GoodsOrderRequest2.setGoodsCount(1L);
        orderGoodsList.add(GoodsOrderRequest2);

        //========= 请求对象 =========
        ConsumeOrderRequest createOrderRequest = new ConsumeOrderRequest();
        createOrderRequest.setBuyLoginAccount("15817161961");
        createOrderRequest.setNote("消费下单");
        //请求对象加入商品列表
        createOrderRequest.setOrderGoodsList(orderGoodsList);
        //创建订单信息
        BaseTest.request.setRequest(createOrderRequest);
    }

    /**
     * mock测试
     */
    @Override
    public void fullMockInner() {
        //商品详情查询错误
        Mockito.doReturn(MockResHelp.skuDetailFail()).when(skuService).getSkuDetail(Mockito.any(CommonRequest.class));
    }
}
