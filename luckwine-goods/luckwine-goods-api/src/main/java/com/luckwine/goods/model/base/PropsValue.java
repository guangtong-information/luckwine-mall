package com.luckwine.goods.model.base;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品属性value
 */
@Data
@Table(name = "props_value")
public class PropsValue implements Serializable {

    @Id
    private Long id;

    /** keyId */
    private Long keyId;

    /** value */
    private String value;

    /** 创建时间 */
    @OrderBy("desc")
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
