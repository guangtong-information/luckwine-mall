package com.luckwine.goods.model.request.sku;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *  库存修改.
 */
@Data
@ToString(callSuper = true)
public class SkuStockModifyRequest extends BaseRequest {

    @NotEmpty
    @Valid
    private List<SkuStockVO> stocks;


}
