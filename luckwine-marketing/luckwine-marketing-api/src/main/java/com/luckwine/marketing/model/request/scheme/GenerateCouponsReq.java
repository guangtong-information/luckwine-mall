package com.luckwine.marketing.model.request.scheme;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class GenerateCouponsReq extends BaseRequest {

    @NotNull(message = "营销方案ID不能为空")
    private String schemeId;

}
