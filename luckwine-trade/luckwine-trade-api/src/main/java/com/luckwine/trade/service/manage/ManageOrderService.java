package com.luckwine.trade.service.manage;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.model.request.CancelOrderRequest;

/**
 * 管理订单服务
 * Created by Winlone on 2018/9/20.
 */
public interface ManageOrderService {

    /**
     * 取消订单
     * @param request
     * @return
     */
    CommonResponse<Boolean> cancelOrder(CommonRequest<CancelOrderRequest> request);
}
