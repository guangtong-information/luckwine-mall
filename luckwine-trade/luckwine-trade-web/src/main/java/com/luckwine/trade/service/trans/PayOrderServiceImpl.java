package com.luckwine.trade.service.trans;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.constant.IntegrationChannel;
import com.luckwine.trade.integration.gateway.TradeGateWay;
import com.luckwine.trade.model.request.PayOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.model.response.PayOrderRes;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 支付订单服务（收银台服务）
 * Created by Winlone on 2018/9/20.
 */
@DubboService(validation = "false")
public class PayOrderServiceImpl implements PayOrderService {

    @Autowired
    private TradeGateWay tradeGateWay;

    /**
     * 余额支付
     *
     * @param request
     * @return
     */
    public CommonResponse<CreateOrderRes> balancePay(CommonRequest<PayOrderRequest> request) {
        //下单请求对象
        TradeCarrier tradeCarrier = new TradeCarrier();
        tradeCarrier.setRequest(request);
        tradeCarrier.setIntegrationChannel(IntegrationChannel.BALANCE_PAY_CHANNEL);

        //创建消息对象
        Message<?> requestMessage = MessageBuilder.withPayload(tradeCarrier)
                .setHeader("traceId", request.getTraceId())
                .setHeader("channelCode", request.getChannelCode())
                .setHeader("operLevel", request.getOperLevel())
                .setHeader("appname", request.getAppName())
                .build();

        //发起工作流
        return tradeGateWay.sendRequest(requestMessage);
    }

    /**
     * 支付宝二维码支付
     *
     * @param request
     * @return
     */
    public CommonResponse<PayOrderRes> alipayPay(CommonRequest<PayOrderRequest> request) {
        //下单请求对象
        TradeCarrier tradeCarrier = new TradeCarrier();
        tradeCarrier.setRequest(request);
        tradeCarrier.setIntegrationChannel(IntegrationChannel.ALIPAY_CHANNEL);

        //创建消息对象
        Message<?> requestMessage = MessageBuilder.withPayload(tradeCarrier)
                .setHeader("traceId", request.getTraceId())
                .setHeader("channelCode", request.getChannelCode())
                .setHeader("operLevel", request.getOperLevel())
                .setHeader("appname", request.getAppName())
                .build();

        //发起工作流
        return tradeGateWay.sendRequest(requestMessage);
    }
}
