package com.luckwine.goods.model.enums;

import lombok.Getter;

/** 搜索排序 */
public enum SearchSort {
    PRICE_ASC("价格升序"), PRICE_DESC("价格降序");

    SearchSort(String desc) {
        this.desc = desc;
    }

    @Getter
    private String desc;
}
