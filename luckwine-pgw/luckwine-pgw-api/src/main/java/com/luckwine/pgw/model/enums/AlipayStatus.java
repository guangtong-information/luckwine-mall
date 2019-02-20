package com.luckwine.pgw.model.enums;

import lombok.Getter;

public enum AlipayStatus {
    TRADE_FINISHED("交易完成"),TRADE_SUCCESS("交易成功"),WAIT_BUYER_PAY("交易创建"),TRADE_CLOSED("交易关闭");

    @Getter
    private String desc;

    AlipayStatus(String desc) {
        this.desc = desc;
    }

    public static PayOrderStatus convert(String statusStr) {
        AlipayStatus status = AlipayStatus.valueOf(statusStr);
        switch (status) {
            case TRADE_SUCCESS: return PayOrderStatus.SUCCESS;
            case TRADE_FINISHED: return PayOrderStatus.SUCCESS;
            case TRADE_CLOSED: return PayOrderStatus.CLOSED;
            case WAIT_BUYER_PAY: return PayOrderStatus.CREATE;
            default: return PayOrderStatus.CREATE;
        }
    }

}
