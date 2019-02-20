package com.luckwine.trade.service.trans;


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
public interface CartService {

    /**
     * 加入购物车
     * @param request
     * @return
     */
    CommonResponse<Boolean> addSkuFromCart(CommonRequest<AddCartRequest> request);

    /**
     * 删除购物车
     * @param request
     * @return
     */
    CommonResponse<Boolean> removeSkuFromCart(CommonRequest<RemoveCartRequest> request);

    /**
     * 查询购物车列表
     * @param request
     * @return
     */
    CommonResponse<List<ShopCartRes>> queryCart(CommonRequest<QueryCartRequest> request);

    /**
     * 查询购物车数量
     * @param request
     * @return
     */
    CommonResponse<ShopCartSumRes> queryCartSum(CommonRequest<QueryCartRequest> request);
}
