package com.luckwine.goods.model.request.sku;

import com.luckwine.goods.model.enums.StockOperate;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class SkuStockVO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private StockOperate stockOperate;

    @NotNull
    @Min(1)
    private Long quantity;

    @NotNull
	private Date updateTime;
}
