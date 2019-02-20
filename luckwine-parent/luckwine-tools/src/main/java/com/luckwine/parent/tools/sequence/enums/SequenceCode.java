package com.luckwine.parent.tools.sequence.enums;


import lombok.Getter;

/**
 * 账号1开头, 订单2开头 ，支付3开头
 */
public enum SequenceCode {

    ACCT("10", "余额账户号"),
    EXPENSE("11", "账户流水号"),
    MAINORDER("20", "主订单号"),
    SUBORDER("21", "子订单号"),
    GOODSORDER("22", "商品单号"),
    WORKORDER("23", "物流单号"),
    CAPITAL("24","资金流水号"),
    PAYMENT("30","支付流水号"),
    TRACEID("99","日志追踪ID");

    @Getter
    private String code;

    @Getter
    private String desc;

    SequenceCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(String code) {
        for (SequenceCode dealTypeEnum : SequenceCode.values()) {
            if (dealTypeEnum.code.equals(code)) {
                return dealTypeEnum.desc;
            }
        }
        return code;
    }

}
