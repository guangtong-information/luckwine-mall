package com.luckwine.goods.model.request.spu;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString(callSuper = true)
public class SpuDetailRequest extends BaseRequest {

    @NotBlank
    private String id;

}
