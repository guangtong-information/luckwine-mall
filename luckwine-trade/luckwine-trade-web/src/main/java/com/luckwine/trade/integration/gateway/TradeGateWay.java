package com.luckwine.trade.integration.gateway;

import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;

/**
 * 交易工作流入口
 * Created by Winlone on 2018/9/20.
 */
public interface TradeGateWay {

    @Gateway(requestChannel = "paramValidateChannel")
    public CommonResponse sendRequest(Message<?> baseCarrier);

}
