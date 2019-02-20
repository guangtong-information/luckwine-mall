package com.luckwine.synthesize.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "ad_module")
public class AdModule implements Serializable {

    @Id
    private String adModuleId;

    // 广告模块的编号
    private String number;

    // 名称
    private String name;

    // 所属系统编码
    private String systemId;

    // 所属页面编码
    private String pageId;

    // 启用状态(1:启用,0:禁用)
    private Integer status;

    // 备注
    private String remarks;

    // 操作时间
    private Date operatingTime;

}