package com.luckwine.trade.service.test.payOrder;

import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.enums.PayChannel;
import com.luckwine.trade.model.request.PayOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.model.response.PayOrderRes;
import com.luckwine.trade.service.test.base.BaseTest;
import com.luckwine.trade.service.trans.PayOrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 下单成功测试
 */
public class PayOrderServiceTest extends BaseTest {


    @Autowired
    private PayOrderService payOrderService;


    /**
     * 余额支付
     */
    @Test
    public void testBalancePay() {

        //========= 请求对象 =========
        PayOrderRequest request = new PayOrderRequest();
        request.setMainOrderCode("2021811251412239433761004");
        request.setBuyLoginAccount("15918837235");
        request.setNote("余额支付");

        //创建订单信息
        BaseTest.request.setRequest(request);

        //下单工作流
        CommonResponse<CreateOrderRes> res = payOrderService.balancePay(BaseTest.request);
        System.out.println("支付结果：" + res);
    }

    /**
     * 支付宝二维码支付
     */
    @Test
    public void testQRPayAlipay() {

        //========= 请求对象 =========
        PayOrderRequest request = new PayOrderRequest();
        request.setMainOrderCode("2021811251211008892281004");
        request.setPayChannle(PayChannel.QRPay);
        request.setBuyLoginAccount("15918837235");
        request.setNote("支付宝二维码支付");

        //创建订单信息
        BaseTest.request.setRequest(request);

        //下单工作流
        CommonResponse<PayOrderRes> res = payOrderService.alipayPay(BaseTest.request);
        System.out.println("支付下单结果：" + res);
    }

    /**
     * 支付宝PC支付
     */
    @Test
    public void testPcAlipay() {

        //========= 请求对象 =========
        PayOrderRequest request = new PayOrderRequest();
        request.setMainOrderCode("2022008212118345341361004");
        request.setPayChannle(PayChannel.PCPay);
        request.setBuyLoginAccount("15918837235");
        request.setNote("支付宝PC支付");

        //创建订单信息
        BaseTest.request.setRequest(request);

        //下单工作流
        CommonResponse<PayOrderRes> res = payOrderService.alipayPay(BaseTest.request);
        System.out.println("支付下单结果：" + res);
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

    }
}
