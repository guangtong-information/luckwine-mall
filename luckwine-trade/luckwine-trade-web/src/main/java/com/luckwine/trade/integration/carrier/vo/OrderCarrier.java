package com.luckwine.trade.integration.carrier.vo;

import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeGoodsOrder;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.entity.TradeSubOrder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class OrderCarrier {
    /**
     * 主单
     */
    private TradeMainOrder tradeMainOrder;

    /**
     * 子单列表
     */
    private List<TradeSubOrder> tradeSubOrder;

    /**
     * 商品单列表
     */
    private List<TradeGoodsOrder> tradeGoodsOrder;

    /**
     * 支付资金流水
     */
    private TradeCapitalStatement tradeCapitalStatement;

    /**
     * 收款资金流水
     */
    private TradeCapitalStatement recTradeCapitalStatement;

    /**
     * 优惠资金流水
     */
    private TradeCapitalStatement discountTradeCapitalStatement;

    /**
     * 支付下单返回的信息
     */
    private String payBody;
}