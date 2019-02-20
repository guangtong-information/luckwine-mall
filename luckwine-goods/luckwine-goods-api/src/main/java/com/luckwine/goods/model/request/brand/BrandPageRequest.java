package com.luckwine.goods.model.request.brand;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class BrandPageRequest extends BaseRequest {

    private String brandName;

}
