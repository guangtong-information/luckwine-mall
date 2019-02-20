package com.luckwine.goods.model.request.brand;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


/**
 * 新增品牌
 */
@Data
@ToString(callSuper = true)
public class BrandSaveRequest extends BaseRequest {

    @NotBlank
    private String brandName;

}
