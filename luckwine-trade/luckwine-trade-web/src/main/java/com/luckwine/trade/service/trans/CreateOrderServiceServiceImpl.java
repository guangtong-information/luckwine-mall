package com.luckwine.trade.service.trans;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.constant.IntegrationChannel;
import com.luckwine.trade.integration.gateway.TradeGateWay;
import com.luckwine.trade.model.request.ConsumeOrderRequest;
import com.luckwine.trade.model.request.RechargeOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 下单服务
 */
@DubboService(validation = "false")
public class CreateOrderServiceServiceImpl implements CreateOrderService {

    @Autowired
    private TradeGateWay tradeGateWay;

    /**
     * 消费下单
     *
     * @param request
     * @return 下单信息
     */
    public CommonResponse<CreateOrderRes> consumeCreateOrder(CommonRequest<ConsumeOrderRequest> request) {
        //下单请求对象
        TradeCarrier tradeCarrier = new TradeCarrier();
        tradeCarrier.setRequest(request);
        tradeCarrier.setIntegrationChannel(IntegrationChannel.CONSUME_ORDER_CHANNEL);

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
     * 充值下单
     * @param request
     * @return
     */
    public  CommonResponse<CreateOrderRes> rechargeCreateOrder(CommonRequest<RechargeOrderRequest> request){
        //下单请求对象
        TradeCarrier tradeCarrier = new TradeCarrier();
        tradeCarrier.setRequest(request);
        tradeCarrier.setIntegrationChannel(IntegrationChannel.RECHARGE_ORDER_CHANNEL);

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
