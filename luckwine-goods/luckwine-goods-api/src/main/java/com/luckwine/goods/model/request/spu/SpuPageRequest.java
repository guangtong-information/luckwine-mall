package com.luckwine.goods.model.request.spu;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SpuPageRequest extends BaseRequest {

    private String goodsName;
}
