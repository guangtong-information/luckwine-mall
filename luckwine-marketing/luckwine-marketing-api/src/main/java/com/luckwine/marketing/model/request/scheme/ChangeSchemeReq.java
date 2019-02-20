package com.luckwine.marketing.model.request.scheme;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class ChangeSchemeReq extends BaseRequest {

    @NotNull(message = "营销方案ID不能为空")
    private String schemeId;

    @NotNull(message = "停用/启用状态不能为空")
    private String status;

}
