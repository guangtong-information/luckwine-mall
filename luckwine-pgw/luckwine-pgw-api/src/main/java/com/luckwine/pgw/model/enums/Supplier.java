package com.luckwine.pgw.model.enums;


import lombok.Getter;

/**
 * 供应商
 */
public enum Supplier {
    ALIPAY("支付宝"),ALLINPAY("通联支付");

    Supplier(String desc) {
        this.desc = desc;
    }

    @Getter
    private String desc;
}
