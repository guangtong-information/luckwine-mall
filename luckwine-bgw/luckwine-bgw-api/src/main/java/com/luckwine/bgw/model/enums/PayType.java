package com.luckwine.bgw.model.enums;

import lombok.Getter;

/**
 * 运费支付方式
 */
public enum PayType {

    CashCollect("1","现付"),
    FreightCollect("2","到付"),
    MonthlyCollect("3","月结"),
    ThirdCollect("4","第三方付(仅SF支持)");

    @Getter
    private final String code;

    @Getter
    private final String desc;

    PayType(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

}
