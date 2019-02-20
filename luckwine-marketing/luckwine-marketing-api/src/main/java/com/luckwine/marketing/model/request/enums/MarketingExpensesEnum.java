package com.luckwine.marketing.model.request.enums;

import lombok.Getter;

public enum MarketingExpensesEnum {
    // 0:成功 1:冲正
    STATUS_SUCCESS("0","成功"),
    SCHEME_WASHED("1","冲正"),
    ;

    @Getter
    private String code;

    @Getter
    private String name;

    MarketingExpensesEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
