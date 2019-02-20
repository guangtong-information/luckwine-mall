package com.luckwine.goods.model.request.sku;

import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *  状态修改.
 */
@Data
@ToString(callSuper = true)
public class SkuStatusModifyRequest extends BaseRequest {

    @NotNull
    private Long id;

    @NotNull
    private SkuStatus skuStatus;

    @NotNull
	private Date updateTime;
    
}
