package com.luckwine.marketing.model.request.enums;

import lombok.Getter;

public enum MarketingSchemeEnum {
    // 方案状态：启用enabled 停用disable 已失效invalid
    SCHEME_STAT_ENABLED("enabled", "启用"),
    SCHEME_STAT_DISABLE("disable", "停用"),
    SCHEME_STAT_INVALID("invalid", "已失效"),
    //方案分类：卡券-coupon 优惠-discount
    SCHEME_TYPE_COUPON("coupon", "卡券"),
    SCHEME_TYPE_DISCOUNT("discount", "优惠"),
    //方案优惠对象：0.全场商品 1.指定商品（本期不实现）
    SCHEME_OBJ_ALL("0","全场商品"),
    SCHEME_OBJ_SKU("1","指定商品"),
    //满足使用的规则：满额full_amount，满减full_discount
    USE_RULE_FULL_AMOUNT("full_amount","满额"),
    USE_RULE_FULL_DISCOUNT("full_discount","满减"),
    // 优惠单位：元rmb, 折扣discount
    DISCOUNT_UINT_RMB("rmb","元"),
    DISCOUNT_UINT_DISCOUNT("discount","折扣"),

    // 是否已经生成优惠券：0.未生成 1.已生成
    GEN_COUPON_FALSE("0","未生成",false),
    GEN_COUPON_TRUE("1","已生成",true),

    //是否上架领券中心:0.未上架 1.上架
    ONLINE_COUPON_CENTER_FALSE("0","未上架",false),
    ONLINE_COUPON_CENTER_TRUE("1","上架",true),
    ;

    @Getter
    private String code;

    @Getter
    private String name;

    @Getter
    private boolean whether;

    MarketingSchemeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    MarketingSchemeEnum(String code, String name, boolean whether) {
        this.code = code;
        this.name = name;
        this.whether = whether;
    }
}
