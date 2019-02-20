package com.luckwine.goods.model.request.sku;

import com.luckwine.goods.model.enums.SearchSort;
import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
public class SearchSkuRequest extends BaseRequest {

    private String text;

    private Long categoryId;

    private Long brandId;

    /** 大于等于金额 */
    private String priceGte;

    /** 小于等于金额 */
    private String priceLte;

    private SearchSort searchSort;

    private SkuStatus skuStatus;

    private SpuStatus spuStatus;

}
