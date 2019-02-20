package com.luckwine.parent.entitybase.enums;

/**
 * 业务状态（业务模块使用）
 */
public enum BizStatus {
    BIZ_WAITING("BIZ_WAITING", "等待发货"),
    BIZ_SUCCEED("BIZ_SUCCEED", "已发货"),
    BIZ_SIGN("BIZ_SIGN", "已签收");

    private final String code;
    private final String desc;

    private BizStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
