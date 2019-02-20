package com.luckwine.trade.service.test.payBack;

import com.luckwine.parent.entitybase.enums.PayStatus;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.date.DateStyle;
import com.luckwine.parent.tools.date.DateUtil;
import com.luckwine.trade.model.request.PayBackRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.service.test.base.BaseTest;
import com.luckwine.trade.service.trans.PayBackService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付回调测试
 */
public class PayBackServiceTest extends BaseTest {


    @Autowired
    private PayBackService payBackService;


    /**
     * 支付回调
     */
    @Test
    public void testPayBack() {
        //========= 请求对象 =========
        PayBackRequest request = new PayBackRequest();
        request.setMainOrderCode("2021811250933594868321004");
        request.setCapSeq("2421811251137132297441002");  //订单资金流水（订单模块的trade_capital_statement表cap_seq资金流水号）
        request.setCapBackSeq("2018112522001434310500407345");  //外部资金流水（支付模块的pay_order表supplier_order_no供应商订单号）
        request.setPayTime(DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS));
        request.setCapAcctCode("2088102175834315");  //外部资金账户号（支付模块的pay_order表supplier_user_account供应商对应用户账号）
        request.setAmount(new BigDecimal(10000.00));
        request.setPayStatus(PayStatus.PAYMENT_SUCCEED);

        //创建订单信息
        BaseTest.request.setRequest(request);

        //下单工作流
        CommonResponse<CreateOrderRes> res = payBackService.payBack(BaseTest.request);
        System.out.println("支付结果：" + res);
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
