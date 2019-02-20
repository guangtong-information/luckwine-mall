package com.luckwine.trade.integration.channel;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InterceptTradeChannel implements ChannelInterceptor {

    /**
     * 发送前
     *
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        MessageHeaders messageHeaders = message.getHeaders();
        String traceId = messageHeaders.get("traceId") != null ? messageHeaders.get("traceId").toString() : "";
        String channelCode = messageHeaders.get("channelCode") != null ? messageHeaders.get("channelCode").toString() : "";
        String operLevel = messageHeaders.get("operLevel") != null ? messageHeaders.get("operLevel").toString() : "";
        String appname = messageHeaders.get("appname") != null ? messageHeaders.get("appname").toString() : "";
        String all = traceId + "," + appname + "," + channelCode + "," + operLevel;

        log.info("[{}] preSend，渠道名：{}，消息：{}", all, channel, message);
        return message;
    }

    /**
     * 发送时
     *
     * @param message 消息内容
     * @param channel 到达的消息渠道
     * @param sent    发送是否成功
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        //log.info("postSend，消息：{}，渠道名：{}，发送是否成功：{}", message, channel, sent);
    }

    /**
     * 发送结束
     *
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        //log.info("afterSendCompletion，消息：{}，渠道名：{}，发送是否成功：{}，异常：{}", message, channel, sent, ex);
    }

    public boolean preReceive(MessageChannel channel) {
        //log.info("preReceive，渠道名：{}", channel);
        return true;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        //log.info("postReceive，消息：{}，渠道名：{}", message, channel);
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        //log.info("afterReceiveCompletion，消息：{}，渠道名：{}，异常：{}", message, channel, ex);
    }


}
