package com.luckwine.parent.entitybase.enums;

import lombok.Getter;

/**
 * 操作级别
 */
public enum OperLevel {
    ADVANCED("ADVANCED", "涉及资金往来和身份认证，如：交易、代理经纪人认证、银行绑卡、订单调价"),
    INTERMEDIATE("INTERMEDIATE", "有更新数据库数据，资金查询，如：后台编辑商品、设置用户、设置方案等"),
    PRIMARY("PRIMARY", "基础操作  如：登陆注册、加入购物车"),
    DEFAULT("DEFAULT", "默认");

    @Getter
    private String code;

    @Getter
    private String desc;

    OperLevel(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
