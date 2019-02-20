package com.luckwine.parent.entitybase.enums;

/**
 * 支付状态（支付模块使用）
 */
public enum PayStatus {
    PAYMENT_WAITING("PAYMENT_WAITING", "等待支付"),
    PAYMENT_SUCCEED("PAYMENT_SUCCEED", "支付成功"),
    PAYMENT_FAIL("PAYMENT_FAIL", "支付失败"),
    PAYMENT_TIMEOUT("PAYMENT_TIMEOUT", "支付超时");

    private final String code;
    private final String desc;

    private PayStatus(String code, String desc) {
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
