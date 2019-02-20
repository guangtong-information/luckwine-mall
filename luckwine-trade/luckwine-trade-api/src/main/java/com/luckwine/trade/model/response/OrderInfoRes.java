package com.luckwine.trade.model.response;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 *
 * 订单查询
 */
@Data
@ToString(callSuper = true)
public class OrderInfoRes extends BaseRequest {

    /**
     * 主单
     */
    private MainOrderRes mainOrderRes;

    /**
     * 子单
     */
    private List<TradeSubOrderRes> tradeSubOrderRes;

    /**
     * 商品单
     */
    private List<TradeGoodsOrderRes> tradeGoodsOrderRes;

    /**
     * 资金流水单
     */
    private List<TradeCapitalStatementRes> tradeCapitalStatementRes;
}
