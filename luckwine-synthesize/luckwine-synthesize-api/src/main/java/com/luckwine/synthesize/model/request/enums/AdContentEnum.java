package com.luckwine.synthesize.model.request.enums;

import lombok.Getter;

public enum AdContentEnum {
    // 投放状态(0:待生效,1:已生效,2:已下架)
    STATUS_0("0", "待生效"),
    STATUS_1("1", "已生效"),
    STATUS_2("2", "已下架"),
    ;

    @Getter
    private String code;

    @Getter
    private String bizName;

    AdContentEnum(String code, String bizName) {
        this.code = code;
        this.bizName = bizName;
    }
}
