package com.luckwine.trade.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 商品交易请求
 * Created by Winlone on  2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class GoodsOrderRequest {

    /**
     * sku规格id
     */
    @NotBlank(message = "sku规格id不能为空")
    private String skuId;

    /**
     * 购买商品数量
     */
    @Min(value = 1,message = "购买商品数量不能小于1")
    private Long goodsCount;

    /**
     * 购物车id（购买商品后，用于扣减购物车的商品）
     */
    private String cartId;
}
