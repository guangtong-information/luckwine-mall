package com.luckwine.trade.integration.service.business.common;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.enums.OrderStatus;
import com.luckwine.parent.entitybase.enums.PayStatus;
import com.luckwine.parent.entitybase.enums.TransType;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.tools.date.DateUtil;
import com.luckwine.trade.dao.TradeCapitalStatementMapper;
import com.luckwine.trade.dao.TradeMainOrderMapper;
import com.luckwine.trade.dao.TradeSubOrderMapper;
import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.entity.TradeSubOrder;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import com.luckwine.trade.model.request.PayBackRequest;
import com.luckwine.trade.model.request.PayOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 支付订单
 * Created by Winlone on 2018/9/20.
 */
@Service
@Slf4j
public class ValidateOrderFlow {

    @Autowired
    private TradeMainOrderMapper tradeMainOrderMapper;

    @Autowired
    private TradeSubOrderMapper tradeSubOrderMapper;

    @Autowired
    private TradeCapitalStatementMapper tradeCapitalStatementMapper;

    /**
     * 支付订单校验
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier payOrderValidate(TradeCarrier tradeCarrier) {
        CommonRequest<PayOrderRequest> request = tradeCarrier.getRequest();
        PayOrderRequest payOrderRequest = request.getRequest();

        //核心订单载体
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();

        //查询主单信息
        TradeMainOrder tradeMainOrder = tradeMainOrderMapper.selectByPrimaryKey(payOrderRequest.getMainOrderCode());
        orderCarrier.setTradeMainOrder(tradeMainOrder);

        //============= 校验 ==================
        //主单号是否存在
        if (tradeMainOrder == null) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "主单号错误");
        }
        //下单用户校验
        if (!tradeMainOrder.getBuyLoginAccount().equals(payOrderRequest.getBuyLoginAccount())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "支付用户和下单用户登录号不匹配");
        }
        //订单状态校验
        if (!OrderStatus.PAYMENT_WAITING.getCode().equals(tradeMainOrder.getOrderStatus())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "当前订单状态为" + tradeMainOrder.getOrderStatus() + "不可支付");
        }

        return tradeCarrier;
    }

    /**
     * 支付回调校验
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier payBackalidate(TradeCarrier tradeCarrier) {
        CommonRequest<PayBackRequest> request = tradeCarrier.getRequest();
        PayBackRequest payBackRequest = request.getRequest();

        //核心订单载体
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();

        //查询主单信息
        TradeMainOrder tradeMainOrder = tradeMainOrderMapper.selectByPrimaryKey(payBackRequest.getMainOrderCode());
        orderCarrier.setTradeMainOrder(tradeMainOrder);

        //============= 校验 ==================
        //主单号是否存在
        if (tradeMainOrder == null) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "主单号错误");
        }
        //订单状态校验
        if (!OrderStatus.PAYMENT_WAITING.getCode().equals(tradeMainOrder.getOrderStatus())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "当前订单状态为" + tradeMainOrder.getOrderStatus() + "不可支付");
        }
        //金额校验
//        if (tradeMainOrder.getPayAmount() != payBackRequest.getAmount()) {
//            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "回调金额[" + payBackRequest.getAmount()
//                    + "] 与 下单金额[" + tradeMainOrder.getPayAmount() + "]不一致");
//        }
        return tradeCarrier;
    }

    /**
     * 子单公共校验
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier commSubOrderValidate(TradeCarrier tradeCarrier) {
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();

        //查询子单信息
        List<TradeSubOrder> tradeSubOrder = tradeSubOrderMapper.selectByMainOrderCode(tradeMainOrder.getMainOrderCode());
        orderCarrier.setTradeSubOrder(tradeSubOrder);

        return tradeCarrier;
    }

    /**
     * 资金流水校验
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier capitalStatementValidate(TradeCarrier tradeCarrier) {
        CommonRequest<PayBackRequest> requestCommonRequest = tradeCarrier.getRequest();
        //核心订单载体
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        //主单载体
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();
        //请求实体
        PayBackRequest payBackRequest = requestCommonRequest.getRequest();
        TradeCapitalStatement tradeCapitalStatement = tradeCapitalStatementMapper.selectByPrimaryKey(payBackRequest.getCapSeq());
        //资金流水不存在
        if (tradeCapitalStatement == null) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "资金流水不存在");
        }
        //[资金流水]付款状态不是待支付,不允许支付回调
        if (!PayStatus.PAYMENT_WAITING.getCode().equals(tradeCapitalStatement.getPayStatus())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "[资金流水]付款状态不是待支付,不允许支付回调");
        }
        //[主单]付款状态不是待支付,不允许支付回调
        if (!OrderStatus.PAYMENT_WAITING.getCode().equals(tradeMainOrder.getOrderStatus())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "[主单]付款状态不是待支付,不允许支付回调");
        }

        //资金流水回调的信息
        tradeCapitalStatement.setPayStatus(payBackRequest.getPayStatus().getCode());
        tradeCapitalStatement.setPayTime(DateUtil.StringToDate(payBackRequest.getPayTime()));
        tradeCapitalStatement.setCapBackSeq(payBackRequest.getCapBackSeq());   //支付返回流水
        tradeCapitalStatement.setCapAcctCode(payBackRequest.getCapAcctCode());   //支付账号
        tradeCapitalStatement.setUpdateTime(new Date());

        //资金流水设置
        tradeCarrier.getOrderCarrier().setTradeCapitalStatement(tradeCapitalStatement);

        return tradeCarrier;
    }

    /**
     * 余额支付特殊校验
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier balanceValidate(TradeCarrier tradeCarrier) {
        //核心订单载体
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        //主单载体
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();
        if (TransType.RECHARGE.getCode().equals(tradeMainOrder.getTransType())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "充值订单不可余额支付");
        }

        return tradeCarrier;
    }

}
