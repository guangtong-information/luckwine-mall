package com.luckwine.parent.entitybase.enums;

public enum PayOper {
    PAY("PAY", "付款"),
    RECEIVE("RECEIVE", "收款");

    private final String code;
    private final String desc;

    private PayOper(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
