package com.luckwine.goods.model.base.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SkuDetail implements Serializable {

    private String spuId;

    /** 商品名称 */
    private String goodsName;

    /** 分类id */
    private Long categoryId;

    /** 分类名称*/
    private String categoryName;

    /** 品牌id */
    private Long brandId;

    /** 品牌名称*/
    private String brandName;

    /** 商品标题 */
    private String title;

    /** 商品属性 */
    private String props;

    /** 商品属性描述 */
    private String propsStr;

    /**主图 */
    private String picPath;

    /** 商品详情 */
    private String detail;

    /** 状态 */
    private String status;

    /** 创建时间 */
    private Date spuCreateTime;

    private Long skuId;

    private String skuName;

    private String skuProps;

    private String skuPropsStr;

    /** 库存 */
    private Long quantity;

    /** 销售数 */
    private Long saleCount;

    /** 价格 */
    private BigDecimal price;

    /* 状态 */
    private String skuStatus;

    private Date skuCreateTime;

}
