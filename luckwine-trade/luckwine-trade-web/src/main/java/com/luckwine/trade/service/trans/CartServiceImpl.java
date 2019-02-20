package com.luckwine.trade.service.trans;


import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.model.request.AddCartRequest;
import com.luckwine.trade.model.request.QueryCartRequest;
import com.luckwine.trade.model.request.RemoveCartRequest;
import com.luckwine.trade.model.response.ShopCartRes;
import com.luckwine.trade.model.response.ShopCartSumRes;

import java.util.List;

/**
 * 购物车服务
 * Created by Winlone on 2018/10/20.
 */
@Service(validation = "true")
public class CartServiceImpl implements CartService {

    public CommonResponse<Boolean> addSkuFromCart(CommonRequest<AddCartRequest> request) {
        return null;
    }

    public CommonResponse<Boolean> removeSkuFromCart(CommonRequest<RemoveCartRequest> request) {
        return null;
    }

    public CommonResponse<List<ShopCartRes>> queryCart(CommonRequest<QueryCartRequest> request) {
        return null;
    }

    public CommonResponse<ShopCartSumRes> queryCartSum(CommonRequest<QueryCartRequest> request) {
        return null;
    }
}
