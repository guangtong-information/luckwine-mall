package com.luckwine.goods.model.request.sku;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString(callSuper = true)
public class SkuDetailGetByIdsRequest  extends BaseRequest {

    @NotEmpty
    private List<Long> skuIds;
}
