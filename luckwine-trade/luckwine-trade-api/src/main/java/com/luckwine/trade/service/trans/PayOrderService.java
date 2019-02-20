package com.luckwine.trade.service.trans;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.model.request.PayOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.model.response.PayOrderRes;

/**
 * 支付订单服务（收银台服务）
 * Created by Winlone on 2018/9/20.
 */
public interface PayOrderService {

    /**
     * 余额支付
     *
     * @param request
     * @return
     */
    CommonResponse<CreateOrderRes> balancePay(CommonRequest<PayOrderRequest> request);

    /**
     * 支付宝支付（支持支付方式--- 1.二维码支付：QRPay   2.PC支付：PCPay）
     * @param request
     * @return
     */
    CommonResponse<PayOrderRes> alipayPay(CommonRequest<PayOrderRequest> request);
}
