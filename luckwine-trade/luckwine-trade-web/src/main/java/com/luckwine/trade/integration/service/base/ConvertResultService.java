package com.luckwine.trade.integration.service.base;


import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.integration.carrier.BaseCarrier;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import com.luckwine.trade.model.response.CreateOrderRes;
import com.luckwine.trade.model.response.PayOrderRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 结果转换器
 * Created by Winlone on 2018/9/20.
 */
@Component
@Slf4j
public class ConvertResultService {

    /**
     * 通用返回
     *
     * @param baseCarrier
     * @return
     */
    public CommonResponse outPutResponse(BaseCarrier baseCarrier) {
        CommonResponse response = new CommonResponse<>();
        response.setResponse(baseCarrier.getResult());
        response.setCode(baseCarrier.getResultCode());
        response.setContent(baseCarrier.getResultDesc());
        return response;
    }

    /**
     * 创建订单信息返回值
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier convertCreateOrderResult(TradeCarrier tradeCarrier) {
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();
        //返回外层接口响应实体
        CreateOrderRes orderRes = new CreateOrderRes();
        orderRes.setMainOrderCode(tradeMainOrder.getMainOrderCode());
        orderRes.setCreateTime(tradeMainOrder.getCreateTime().toString());
        orderRes.setPayAmount(tradeMainOrder.getPayAmount());
        orderRes.setTotalAmount(tradeMainOrder.getTotalAmount());
        orderRes.setDiscAmount(tradeMainOrder.getDiscAmount());
        //赋值返回值
        tradeCarrier.setResult(orderRes);
        tradeCarrier.setResultCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        tradeCarrier.setResultDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        return tradeCarrier;
    }

    /**
     * 支付订单信息返回值
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier convertPayOrderResult(TradeCarrier tradeCarrier) {
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();
        TradeCapitalStatement tradeCapitalStatement = orderCarrier.getTradeCapitalStatement();
        //返回外层接口响应实体
        PayOrderRes orderRes = new PayOrderRes();
        orderRes.setMainOrderCode(tradeMainOrder.getMainOrderCode());
        orderRes.setCapSeq(tradeCapitalStatement.getCapSeq());
        orderRes.setCapModule(tradeCapitalStatement.getCapModule());
        orderRes.setCapSysType(tradeCapitalStatement.getCapSysType());
        orderRes.setCreateTime(tradeMainOrder.getCreateTime().toString());
        orderRes.setPayAmount(tradeMainOrder.getPayAmount());
        orderRes.setTotalAmount(tradeMainOrder.getTotalAmount());
        orderRes.setDiscAmount(tradeMainOrder.getDiscAmount());
        orderRes.setBody(orderCarrier.getPayBody());  //支付下单返回信息
        //赋值返回值
        tradeCarrier.setResult(orderRes);
        tradeCarrier.setResultCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        tradeCarrier.setResultDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        return tradeCarrier;
    }
}
