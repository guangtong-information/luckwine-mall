package com.luckwine.synthesize.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "ad_content")
public class AdContent implements Serializable {

    @Id
    private String adContentId;

    // 标题
    private String title;

    // 商品ID
    private Long itemId;

    // 货品价格
    private BigDecimal price;

    // 跳转链接
    private String jumpUrl;

    // 内容类型(1:自定义广告 2:视频广告 3:抽取商品广告)
    private Integer type;

    // 广告图片路径
    private String imageUrl;

    // 简介
    private String introduction;

    // 排序权重
    private Integer sortWeight;

    // 开始时间
    private Date timeStart;

    // 结束时间
    private Date timeEnd;

    // 投放状态(0:待生效,1:已生效,2:已下架)
    private Integer status;

    // 备注
    private String remarks;

    // 广告模块编码
    private String adModuleId;

    // 操作时间
    private Date operatingTime;
}