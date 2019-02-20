package com.luckwine.trade.integration.constant;

import lombok.Getter;

/**
 * 异常操作
 */
public enum ExceptionDeal {
    ROLLBACKSTOCKS("ROLLBACKSTOCKS", "回滚商品库存"),
    ;

    @Getter
    private final String code;

    @Getter
    private final String desc;

    ExceptionDeal(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
