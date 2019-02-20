package com.luckwine.goods.model.base;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品属性key
 */
@Data
@Table(name = "props_key")
public class PropsKey implements Serializable {

    @Id
    private Long id;

    /** key */
    @Column(name = "`key`")
    private String key;

    /** 创建时间 */
    @OrderBy("desc")
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
