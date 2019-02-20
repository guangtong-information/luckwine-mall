package com.luckwine.goods.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品spu
 */
@Data
@Table(name = "spu")
public class Spu implements Serializable {

    @Id
    private String id;

    /** 商品名称 */
    private String goodsName;

    /** 分类id */
    private Long categoryId;

    /** 品牌id */
    private Long brandId;

    /** 商品标题 */
    private String title;

    /** 商品属性 */
    private String props;

    private String propsStr;

    /**主图 */
    private String picPath;

    /** 商品详情 */
    private String detail;

    /** 状态 */
    private String status;

    /** 创建时间 */
    @OrderBy("desc")
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;



}
