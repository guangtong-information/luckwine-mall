package com.luckwine.bgw.model.request;

import com.luckwine.bgw.model.enums.ShipperCode;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString(callSuper = true)
public class EOrderTracesGetReq extends BaseRequest {

    // 子单号
    private String orderCode;

    @NotBlank(message = "快递单号不能为空")
    private String LogisticCode;

    @NotBlank(message = "快递公司编码不能为空")
    private String ShipperCode;

}
