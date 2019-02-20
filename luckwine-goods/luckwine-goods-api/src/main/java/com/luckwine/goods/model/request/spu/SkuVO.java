package com.luckwine.goods.model.request.spu;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
public class SkuVO extends BaseRequest {

    @NotBlank
    private String props;

    @NotBlank
    private String skuName;

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal price;

}
