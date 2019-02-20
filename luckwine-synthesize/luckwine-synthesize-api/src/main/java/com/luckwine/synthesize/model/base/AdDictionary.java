package com.luckwine.synthesize.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "ad_dictionary")
public class AdDictionary implements Serializable {

    @Id
    private Integer id;

    // 名称
    private String name;

    // 访问路径
    private String url;

    // 排序
    private Integer sortWeight;

    // 是否激活 0.未激活 1.激活
    private Integer status;

    // 父字典编码
    private String parentId;

}