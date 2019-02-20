package com.luckwine.goods.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类
 */
@Data
@Table(name = "category")
public class Category implements Serializable {

    @Id
    private Long id;

    /** 分类名称 */
    private String categoryName;

    /** 创建时间 */
    @OrderBy("desc")
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
