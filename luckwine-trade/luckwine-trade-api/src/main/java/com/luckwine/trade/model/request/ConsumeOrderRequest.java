package com.luckwine.trade.model.request;

import com.luckwine.trade.model.request.base.TransBaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 下单请求
 * Created by Winlone on  2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class ConsumeOrderRequest extends TransBaseRequest {

    /**
     * 下单的商品列表
     */
    @Size(min = 1,message = "购买商品数量不能小于1")
    private List<GoodsOrderRequest> orderGoodsList;

    /**
     * 使用优惠券列表
     */
    private List<CouponRequestSeq> marketRequestSeqList;
}
