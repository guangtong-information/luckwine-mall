package com.luckwine.parent.entitybase.enums;

import lombok.Getter;

/**
 * 应用名称
 */
public enum AppName {

    PORTALSERVICE("LUCKWINE-PORTAL-SERVICE", "前端网站服务层", "01"),
    OSSSERVICE("LUCKWINE-OSS-SERVICE", "OSS运管系统服务层", "02"),
    CUSTOMER("LUCKWINE-CUSTOMER", "客户中心", "03"),
    GOODS("LUCKWINE-GOODS", "商品系统", "04"),
    ACCT("LUCKWINE-ACCT", "账户系统", "05"),
    PGW("LUCKWINE-PGW", "支付网关", "06"),
    BGW("LUCKWINE-BGW", "业务网关", "07"),
    TRADE("LUCKWINE-TRADE", "交易中心", "08"),
    MARKETING("LUCKWINE-MARKETING", "营销中心", "09"),
    SYNTHESIZE("LUCKWINE-SYNTHESIZE", "综合域", "10"),
    SEARCH("LUCKWINE-SEARCH", "统一搜索服务", "11"),
    SPIDER("LUCKWINE-SPIDER", "爬虫模块", "12"),
    ;

    @Getter
    private final String code;

    @Getter
    private final String desc;

    @Getter
    private final String number;

    AppName(String code, String desc, String number) {
        this.code = code;
        this.desc = desc;
        this.number = number;
    }


}
