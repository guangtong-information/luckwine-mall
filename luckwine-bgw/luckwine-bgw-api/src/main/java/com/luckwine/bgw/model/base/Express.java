package com.luckwine.bgw.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Table(name = "express")
public class Express extends BaseRequest {

    @Id
    private String id;

    // 快递公司编码
    private String shipperCode;

    // 快递公司名称
    private String shipperName;

    // 快递/快运业务类型
    private String expBisName;

    // 快递鸟接口参数
    private String expType;

    private Date createDate;

    private Date updateDate;

    private String state;

}