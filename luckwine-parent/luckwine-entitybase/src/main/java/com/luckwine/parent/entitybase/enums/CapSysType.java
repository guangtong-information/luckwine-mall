package com.luckwine.parent.entitybase.enums;

public enum CapSysType {
    BALANCE("BALANCE", "余额"),
    ALIPAY("ALIPAY", "支付宝"),
    COUPON("COUPON", "优惠券");

    private final String code;
    private final String desc;

    private CapSysType(String code, String desc) {
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
