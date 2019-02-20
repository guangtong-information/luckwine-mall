package com.luckwine.goods.model.request.brand;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 新增品牌
 */
@Data
@ToString(callSuper = true)
public class BrandModifyRequest extends BaseRequest {


    @NotNull
    private Long id;

    @NotBlank
    private String brandName;

}
