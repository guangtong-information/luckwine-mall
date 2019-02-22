package com.luckwine.portal.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartBase implements Serializable {
    private static final long serialVersionUID = -7651434767644042906L;

    private Long productId;

    private int productNum;

}
