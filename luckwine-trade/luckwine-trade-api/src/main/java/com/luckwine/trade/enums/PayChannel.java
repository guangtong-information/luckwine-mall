package com.luckwine.trade.enums;

import lombok.Getter;

/**
 * 支付渠道
 */
public enum PayChannel {

    QRPay("QRPay", "二维码支付"), PCPay("PCPay", "PC页面支付");

    @Getter
    private final String code;

    @Getter
    private final String desc;


    PayChannel(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
