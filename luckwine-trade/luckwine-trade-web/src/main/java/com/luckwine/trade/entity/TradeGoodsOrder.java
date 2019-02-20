package com.luckwine.trade.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
public class TradeGoodsOrder {
    /**
     * 商品单号
     */
    @Id
    private String goodsOrderCode;

    /**
     * 子单号
     */
    private String subOrderCode;

    /**
     * 主单号
     */
    private String mainOrderCode;

    /**
     * spu商品id--spu表
     */
    private String spuId;

    /**
     * 商品名称--spu表
     */
    private String goodsName;

    /**
     * spu商品属性--spu表
     */
    private String spuPropsStr;

    /**
     * 商品规格id--sku表
     */
    private String skuId;

    /**
     * 商品规格名称--sku表
     */
    private String skuName;

    /**
     * 商品规格属性
     */
    private String skuPropsStr;

    /**
     * 商品分类:1.白酒 2.洋酒 3.葡萄酒 4.啤酒 5.黄酒 6.养生酒--category表
     */
    private String categoryName;

    /**
     * 品牌--brand表
     */
    private String brandName;

    /**
     * 商品图片--spu表
     */
    private String picPath;

    /**
     * 商品单价--sku表
     */
    private BigDecimal goodsPrice;

    /**
     * 商品数
     */
    private Long goodsCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 订单总金额(商品单价*数量-优惠金额)
     */
    private BigDecimal totalAmount;

    /**
     * 实付金额
     */
    private BigDecimal payAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discAmount;

    /**
     * 商品业务状态：BIZ_WAITING("BIZ_WAITING", "等待发货"),BIZ_SUCCEED("BIZ_SUCCEED", "已发货"),BIZ_SIGN("BIZ_SIGN", "已签收");
     */
    private String bizStatus;
}