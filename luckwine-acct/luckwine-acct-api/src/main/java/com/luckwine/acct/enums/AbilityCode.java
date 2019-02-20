package com.luckwine.acct.enums;

import lombok.Getter;

/**
 * 交易类型（订单模块使用）
 */
public enum AbilityCode {

    RECHARGE("0003", "充值"), WITHDRAW("0002", "提现"), CONSUME("0001", "消费");

    @Getter
    private final String code;

    @Getter
    private final String desc;


    AbilityCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
