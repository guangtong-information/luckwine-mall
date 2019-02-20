package com.luckwine.trade.service.trans;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.model.request.ConsumeOrderRequest;
import com.luckwine.trade.model.request.RechargeOrderRequest;
import com.luckwine.trade.model.response.CreateOrderRes;

/**
 * 下单服务
 * Created by Winlone on 2018/9/20.
 */
public interface CreateOrderService {

    /**
     * 消费下单
     *
     * @param request
     * @return
     */
    CommonResponse<CreateOrderRes> consumeCreateOrder(CommonRequest<ConsumeOrderRequest> request);

    /**
     * 充值下单
     * @param request
     * @return
     */
    CommonResponse<CreateOrderRes> rechargeCreateOrder(CommonRequest<RechargeOrderRequest> request);

}
