package com.luckwine.goods.model.enums;

import lombok.Getter;

public enum StockOperate {
    INCR("添加"), DECR("减少");

    @Getter
    private String desc;

    StockOperate(String desc) {
        this.desc = desc;
    }
}
