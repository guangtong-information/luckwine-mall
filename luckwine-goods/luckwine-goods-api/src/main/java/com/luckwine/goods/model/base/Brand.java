package com.luckwine.goods.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *  商品品牌
 */
@Data
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    private Long id;

    /** 品牌名称 */
    private String brandName;

    /** 创建时间 */
    @OrderBy("desc")
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
