package com.luckwine.goods.model.response;

import com.luckwine.goods.model.base.Sku;
import com.luckwine.goods.model.base.Spu;
import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class SpuDetail extends BaseResponse {

     private Spu spu;

     private String categoryName;

     private String brandName;

     private List<Sku> skus;
}
