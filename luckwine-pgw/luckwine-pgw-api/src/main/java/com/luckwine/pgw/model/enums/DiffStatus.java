package com.luckwine.pgw.model.enums;

import lombok.Getter;

public enum  DiffStatus {
    WAIT("待处理"),
    FINISH("处理完成"),
    NO_NEED("不处理");

    @Getter
    private String desc;

    DiffStatus(String desc) {
        this.desc = desc;
    }
}
