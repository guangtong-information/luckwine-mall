package com.luckwine.goods.model.request.spu;

import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Data
@ToString(callSuper = true)
public class SpuModifyRequest extends BaseRequest {

    @NotNull
    private Long id;

    @NotNull
    private SpuStatus spuStatus;

}
