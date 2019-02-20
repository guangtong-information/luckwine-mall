package com.luckwine.parent.entitybase.enums;

/**
 * 订单状态（订单模块使用）
 */
public enum OrderStatus {

    PAYMENT_WAITING("PAYMENT_WAITING","等待支付"),
    PAYMENT_SUCCEED("PAYMENT_SUCCEED","支付成功"),
    ORDER_CANCEL("ORDER_CANCEL","订单取消"),
    ORDER_FINISHED("ORDER_FINISHED","订单完成");

    private final String code;
    private final String desc;

    private OrderStatus(String code, String desc) {
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
