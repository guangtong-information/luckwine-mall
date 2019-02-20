package com.luckwine.parent.entitybase.enums;


/**
 *  环境位
 */
public enum Env {

    DEV("1", "开发环境"),
    TEST("2", "测试环境"),
    PRESSURE("3", "压力测试环境"),
    PROD("0", "生产环境");

    private final String code;
    private final String desc;

    private Env(String code, String desc) {
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
