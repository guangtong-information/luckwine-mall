package com.luckwine.parent.entitybase.enums;

public enum CapModule {
    ACCT("ACCT", "账户中心"),
    PAYMENT("PGW", "支付网关"),
    MARKETING("MARKETING", "营销模块");

    private String code;
    private String desc;

    private CapModule(String code, String desc) {
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
