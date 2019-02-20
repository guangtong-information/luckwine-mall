package com.luckwine.goods.model.response;

import com.luckwine.goods.model.base.Spu;
import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;

@Data
public class SpuInfo extends BaseResponse {

     private Spu spu;

     private String categoryName;

     private String brandName;
}
