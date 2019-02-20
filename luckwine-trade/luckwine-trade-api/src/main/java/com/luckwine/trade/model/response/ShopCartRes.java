package com.luckwine.trade.model.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public class ShopCartRes implements Serializable  {

    /**
     * 购物车id
     */
    private String cardId;

    /**
     * 登录号
     */
    private String loginAccount;

    /**
     * 商品规格
     */
    private String skuId;

    /**
     * 购买商品数量
     */
    private Long count;

    /**
     * 加入时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
