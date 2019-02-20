package com.luckwine.trade.service.info;


import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.model.request.QueryMainOrderRequest;
import com.luckwine.trade.model.request.QueryOrderInfoRequest;
import com.luckwine.trade.model.response.MainOrderRes;
import com.luckwine.trade.model.response.OrderInfoRes;

/**
 * 订单查询
 * Created by Winlone on 2018/10/20.
 */
@Service(validation = "true")
public class QueryOrderServiceImpl implements QueryOrderService {

    public CommonQueryPageResponse<MainOrderRes> queryMainOrderList(CommonQueryPageRequest<QueryMainOrderRequest> request) {
        return null;
    }

    public CommonResponse<OrderInfoRes> queryOrderInfo(CommonRequest<QueryOrderInfoRequest> request) {
        return null;
    }
}
