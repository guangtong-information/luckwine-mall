package com.luckwine.goods.model.enums;

import lombok.Getter;

public enum SpuStatus {

    ON_SALE("出售中"),IN_STOCK("仓库中");

    SpuStatus(String desc) {
        this.desc = desc;
    }

    @Getter
    private String desc;
}
