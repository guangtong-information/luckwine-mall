package com.luckwine.portal.goods;

import lombok.Data;

@Data
public class GoodsComputerRequest {
    private String productId;
    private String productName;
    private String sub_title;
    private String productImageBig;
    private String salePrice;
}
