package com.luckwine.pgw.model.enums;

import lombok.Getter;

public enum  DiffBatchStatus {
    OK("正常"), ERROR("异常");


    @Getter
    private String desc;

    DiffBatchStatus(String desc) {
        this.desc = desc;
    }
}
