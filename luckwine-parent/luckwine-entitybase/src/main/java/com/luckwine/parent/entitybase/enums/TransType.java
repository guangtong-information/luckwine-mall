package com.luckwine.parent.entitybase.enums;

import lombok.Getter;

/**
 * 交易类型（订单模块使用）
 */
public enum TransType {

    RECHARGE("RECHARGE", "充值"), WITHDRAW("WITHDRAW", "提现"), CONSUME("CONSUME", "消费");

    @Getter
    private final String code;

    @Getter
    private final String desc;


    TransType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
