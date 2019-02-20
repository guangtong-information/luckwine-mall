package com.luckwine.goods.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品sku
 */
@Data
@Table(name = "sku")
public class Sku  implements Serializable  {

    @Id
    private Long id;

    private String skuName;

    /** spuId */
    private String spuId;

    /** 属性 */
    @OrderBy("desc")
    private String props;

    private String propsStr;

    /** 库存 */
    private Long quantity;

    /** 销售数 */
    private Long saleCount;

    /** 价格 */
    private BigDecimal price;

    /* 状态 */
    private String status;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;


}
