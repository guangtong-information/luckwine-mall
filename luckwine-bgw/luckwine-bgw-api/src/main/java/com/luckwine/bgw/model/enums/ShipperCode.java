package com.luckwine.bgw.model.enums;

import lombok.Getter;

/**
 * 快递公司编码
 */
public enum ShipperCode {

    SF("SF","顺风"),
    YTO("YTO","圆通");

    @Getter
    private final String code;

    @Getter
    private final String desc;

    ShipperCode(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
