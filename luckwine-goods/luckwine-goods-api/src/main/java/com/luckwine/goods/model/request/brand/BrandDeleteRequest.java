package com.luckwine.goods.model.request.brand;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


/**
 * 删除品牌
 */
@Data
@ToString(callSuper = true)
public class BrandDeleteRequest extends BaseRequest {

    @NotNull
    private Long id;

}
