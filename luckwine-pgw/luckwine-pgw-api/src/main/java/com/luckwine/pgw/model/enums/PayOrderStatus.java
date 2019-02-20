package com.luckwine.pgw.model.enums;

public enum PayOrderStatus {
    CREATE("创建"), SUCCESS("交易成功"), CLOSED("关闭");

    PayOrderStatus(String desc) {
        this.desc = desc;
    }

    private String desc;
}
