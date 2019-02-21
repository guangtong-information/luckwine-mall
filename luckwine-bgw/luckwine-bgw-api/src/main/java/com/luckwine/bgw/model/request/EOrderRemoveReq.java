package com.luckwine.bgw.model.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString(callSuper = true)
public class EOrderRemoveReq extends BaseRequest {

    @NotBlank(message = "子单号不能为空")
    private String orderCode;

    @NotBlank(message = "快递公司编码不能为空")
    private String shipperCode;

    //快递单号不能为空
    @NotBlank(message = "快递单号不能为空")
    @JSONField(name = "ExpNo")
    private String logisticCode;

}
