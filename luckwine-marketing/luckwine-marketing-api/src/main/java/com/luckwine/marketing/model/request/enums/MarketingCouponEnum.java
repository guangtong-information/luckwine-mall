package com.luckwine.marketing.model.request.enums;

import lombok.Getter;

public enum MarketingCouponEnum {

    // 卡券状态：未领取noget、未使用noused、已使用used、过期overdue
    COUPONSTAT_NOGET("noget", "未领取"),
    COUPONSTAT_NOUSED("noused", "未使用"),
    COUPONSTAT_USED("used", "已使用"),
    COUPONSTAT_OVERDUE("overdue", "过期"),
    ;

    @Getter
    private String code;

    @Getter
    private String name;

    MarketingCouponEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
