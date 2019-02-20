package com.luckwine.goods.model.enums;

import lombok.Getter;

public enum SkuStatus {

    ON_SALE("出售中"),IN_STOCK("仓库中");

    SkuStatus(String desc) {
        this.desc = desc;
    }

    @Getter
    private String desc;
}
