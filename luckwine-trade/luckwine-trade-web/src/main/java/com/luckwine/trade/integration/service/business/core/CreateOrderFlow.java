package com.luckwine.trade.integration.service.business.core;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.trade.dao.TradeCapitalStatementMapper;
import com.luckwine.trade.dao.TradeGoodsOrderMapper;
import com.luckwine.trade.dao.TradeMainOrderMapper;
import com.luckwine.trade.dao.TradeSubOrderMapper;
import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeGoodsOrder;
import com.luckwine.trade.entity.TradeSubOrder;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库创建订单
 * Created by Winlone on 2018/9/20.
 */
@Service
@Slf4j
public class CreateOrderFlow {

    @Autowired
    private TradeMainOrderMapper tradeMainOrderMapper;

    @Autowired
    private TradeSubOrderMapper tradeSubOrderMapper;

    @Autowired
    private TradeGoodsOrderMapper tradeGoodsOrderMapper;

    @Autowired
    private TradeCapitalStatementMapper tradeCapitalStatementMapper;

    @Autowired
    private PreOrderFlow consumePreService;


    /**
     * 创建消费订单
     *
     * @param tradeCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TradeCarrier createConsumeOrder(TradeCarrier tradeCarrier) {
        //订单信息载体
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        //插入订单
        int count = 0;
        try {
            //插入主单
            count = count + insertMainOrder(orderCarrier);
            //插入子单
            count = count + inserSubOrder(orderCarrier);
            //插入商品单
            count = count + inserGoodsOrder(orderCarrier);
            //优惠券资金流水单
            count = count + insertCapitalStatement(orderCarrier);
        } catch (Exception e) {
            //回滚库存
            consumePreService.rollbackGoodsStocks(tradeCarrier);
            //抛异常，跳到异常渠道
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(), ResponseCodeConstant.DB_EXCEPTION.getResponseDesc());
        } finally {
            log.info("创建消费订单，插入主单、子单、商品单总条数:{}", count);
            tradeCarrier.setResult(tradeCarrier.getOrderCarrier());
            tradeCarrier.setResultCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            tradeCarrier.setResultDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        }

        return tradeCarrier;
    }

    /**
     * 创建充值单
     *
     * @param tradeCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TradeCarrier createRechargeOrder(TradeCarrier tradeCarrier) {
        //订单信息载体
        OrderCarrier mainOrderCarrier = tradeCarrier.getOrderCarrier();
        //插入订单
        int count = 0;
        try {
            //插入主单
            count = insertMainOrder(mainOrderCarrier);
        } catch (Exception e) {
            //抛异常，跳到异常渠道
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(), ResponseCodeConstant.DB_EXCEPTION.getResponseDesc());
        } finally {
            log.info("创建充值单，插入主单总条数:{}", count);
            tradeCarrier.setResult(tradeCarrier.getOrderCarrier());
            tradeCarrier.setResultCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            tradeCarrier.setResultDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        }
        return tradeCarrier;
    }

    /**
     * 插入主单
     *
     * @param orderCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertMainOrder(OrderCarrier orderCarrier) {
        int tradeMainOrderCount = tradeMainOrderMapper.insertSelective(orderCarrier.getTradeMainOrder());
        return tradeMainOrderCount;
    }

    /**
     * 插入子单
     *
     * @param orderCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int inserSubOrder(OrderCarrier orderCarrier) {
        //子单插入：店铺订单
        TradeSubOrder subOrderCarrier = orderCarrier.getTradeSubOrder().get(0);
        int tradeSubOrderCount = tradeSubOrderMapper.insertSelective(subOrderCarrier);
        return tradeSubOrderCount;
    }

    /**
     * 插入商品单
     *
     * @param orderCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int inserGoodsOrder(OrderCarrier orderCarrier) {
        //商品单
        List<TradeGoodsOrder> subOrderCarrier = orderCarrier.getTradeGoodsOrder();
        int tradeGoodsOrderCount = tradeGoodsOrderMapper.insertBatch(subOrderCarrier);
        return tradeGoodsOrderCount;
    }

    /**
     * 插入资金流水
     *
     * @param orderCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertCapitalStatement(OrderCarrier orderCarrier) {
        TradeCapitalStatement discountTradeCapitalStatement = orderCarrier.getDiscountTradeCapitalStatement();
        //插入资金流水信息
        int count = tradeCapitalStatementMapper.insert(discountTradeCapitalStatement);
        if (count <= 0)
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(),
                    "主单号[" + discountTradeCapitalStatement.getMainOrderCode() + "]生成资金流水异常");

        return count;
    }
}
