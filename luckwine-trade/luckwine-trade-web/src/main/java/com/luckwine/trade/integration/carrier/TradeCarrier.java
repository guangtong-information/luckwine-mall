package com.luckwine.trade.integration.carrier;

import com.luckwine.goods.model.request.sku.SkuStockVO;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import com.luckwine.trade.integration.constant.IntegrationChannel;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 交易工作流载体
 * Created by Winlone on 2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class TradeCarrier<Request extends CommonRequest> extends BaseCarrier<Request, Object> {

    /*
     * 后续路由 默认 无， 结果转换
     */
    private String integrationAfterChannel = IntegrationChannel.CONVERT_RESULT_CHANNEL;

    /**
     * 订单信息载体
     */
    private OrderCarrier orderCarrier;

    /**
     * 批量查询商品sku集合
     */
    private List<Long> skuIds;

    /**
     * 批量减库存集合
     */
    private List<SkuStockVO> stocks;


}
