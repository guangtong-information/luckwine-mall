package com.luckwine.trade.service.test.createOrder;

import com.luckwine.goods.model.service.SkuService;
import com.luckwine.marketing.service.MarketingCouponService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.handle.remote.goods.GetSkuDetailRemoteService;
import com.luckwine.trade.handle.remote.goods.StockModifyRemoteService;
import com.luckwine.trade.handle.remote.marketing.UserCouponsRemoteService;
import com.luckwine.trade.model.request.ConsumeOrderRequest;
import com.luckwine.trade.model.request.CouponRequestSeq;
import com.luckwine.trade.model.request.GoodsOrderRequest;
import com.luckwine.trade.model.request.RechargeOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.service.test.base.BaseTest;
import com.luckwine.trade.service.test.base.MockResHelp;
import com.luckwine.trade.service.trans.CreateOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 下单成功测试
 */
@Slf4j
public class TradeServiceTest extends BaseTest {


    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    @InjectMocks
    private StockModifyRemoteService stockModifyRemoteService;

    @Autowired
    @InjectMocks
    private GetSkuDetailRemoteService getSkuDetailRemoteService;

    @Spy
    private SkuService skuService;

    @Autowired
    @InjectMocks
    private UserCouponsRemoteService userCouponsRemoteService;

    @Spy
    private MarketingCouponService marketingCouponService;

    /**
     * 优惠分摊
     * 流程：购买了3个商品，使用优惠券的优惠金额4元，摊分到每个商品的优惠金额
     * 为什么要摊分优惠金额？因为每个商品单独做退款时候，需要按百分比优惠金额扣除。
     * 结果：第一个优惠金额：1.34   第二个优惠金额：1.33  第三个优惠金额：1.33  总数：4元
     */
    @Test
    public void divide() {
        //总优惠价
        BigDecimal totalDiscount = new BigDecimal(4);
        //商品数量
        BigDecimal goodsCount = new BigDecimal(3);

        //每个商品的优惠价
        BigDecimal goodsDiscountAmount = totalDiscount.divide(goodsCount, 2, RoundingMode.DOWN);
        log.info("每个商品优惠价:{}", goodsDiscountAmount);
        BigDecimal r = totalDiscount.remainder(goodsCount);
        //-1.小于  0.等于 1.大于， 比较后等于1证明是有余数
        if (r.compareTo(BigDecimal.ZERO) == 1) {
            //优惠余数=总优惠价-(每件优惠价*商品数量)
            BigDecimal remainder = totalDiscount.subtract(goodsDiscountAmount.multiply(goodsCount));
            log.info("余数结果:{}", remainder);
            log.info("第一个商品优惠价需要加余数:{}", goodsDiscountAmount.add(remainder));
        } else {
            log.info("没有余数，每个商品优惠价为:{}", goodsDiscountAmount);
        }
    }

    /**
     * 消费下单服务：正常流程
     */
    @Test
    public void testConsumeCreateOrder() {
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

        //========= 优惠券 =========
        List<CouponRequestSeq> marketRequestSeqList = new ArrayList<>();
        CouponRequestSeq couponRequestSeq = new CouponRequestSeq();
        couponRequestSeq.setCouponNum("AAAA");
        List<Long> skuIdSubList = new ArrayList<>();
        skuIdSubList.add(10000093L);
        skuIdSubList.add(10000094L);
        couponRequestSeq.setSkuIdSubList(skuIdSubList);
        marketRequestSeqList.add(couponRequestSeq);
        //========= 优惠券-end =========

        //========= 请求对象 =========
        ConsumeOrderRequest createOrderRequest = new ConsumeOrderRequest();
        createOrderRequest.setBuyLoginAccount("15918837235");
        createOrderRequest.setNote("消费下单");
        //请求对象加入商品列表
        createOrderRequest.setOrderGoodsList(orderGoodsList);
        //优惠券列表
        createOrderRequest.setMarketRequestSeqList(marketRequestSeqList);
        //创建订单信息
        BaseTest.request.setRequest(createOrderRequest);

        //下单工作流
        CommonResponse<CreateOrderRes> res = createOrderService.consumeCreateOrder(BaseTest.request);
        System.out.println("下单结果：" + res);
    }

    /**
     * 充值下单服务：正常流程
     */
    @Test
    public void testRechargeCreateOrder() {

        //========= 请求对象 =========
        RechargeOrderRequest createOrderRequest = new RechargeOrderRequest();
        createOrderRequest.setBuyLoginAccount("15918837235");
        createOrderRequest.setNote("充值下单");
        createOrderRequest.setAmount(new BigDecimal(50));
        //创建订单信息
        BaseTest.request.setRequest(createOrderRequest);

        //下单工作流
        CommonResponse<CreateOrderRes> res = createOrderService.rechargeCreateOrder(BaseTest.request);
        System.out.println("下单结果：" + res);
    }

    /**
     * 通用请求参数
     */
    @Override
    public void initRequestInner() {

    }

    /**
     * mock测试
     */
    @Override
    public void fullMockInner() {
        //商品详情查询模拟
        Mockito.doReturn(MockResHelp.skuDetailSuccess()).when(skuService).getSkuDetail(Mockito.any(CommonRequest.class));
        //商品库存扣减的模拟
        Mockito.doReturn(MockResHelp.stockModifySuccess()).when(skuService).stockModify(Mockito.any(CommonRequest.class));

        //使用优惠券的模拟
        Mockito.doReturn(MockResHelp.userCouponsSuccess()).when(marketingCouponService).userCoupons(Mockito.any(CommonRequest.class));
    }
}
