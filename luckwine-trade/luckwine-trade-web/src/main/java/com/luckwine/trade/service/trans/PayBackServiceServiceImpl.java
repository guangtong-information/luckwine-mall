package com.luckwine.trade.service.trans;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.constant.IntegrationChannel;
import com.luckwine.trade.integration.gateway.TradeGateWay;
import com.luckwine.trade.model.request.PayBackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 支付回调
 */
@Service(validation = "false")
public class PayBackServiceServiceImpl implements PayBackService {

    @Autowired
    private TradeGateWay tradeGateWay;

    /**
     * 支付回调
     *
     * @param request
     * @return
     */
    public CommonResponse<Boolean> payBack(CommonRequest<PayBackRequest> request) {
        //下单请求对象
        TradeCarrier tradeCarrier = new TradeCarrier();
        tradeCarrier.setRequest(request);
        tradeCarrier.setIntegrationChannel(IntegrationChannel.PAY_BACK_CHANNEL);

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
