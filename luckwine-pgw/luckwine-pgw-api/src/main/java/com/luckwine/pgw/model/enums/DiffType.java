package com.luckwine.pgw.model.enums;

import lombok.Getter;

public enum  DiffType {
    EXCESS_MONEY("长款"),
    LACK_MONEY("短款"),
    DIFF_MONEY("差额");


    @Getter
    private String desc;

    DiffType(String desc) {
        this.desc = desc;
    }
}
