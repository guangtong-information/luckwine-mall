package com.luckwine.pgw.model.enums;

import lombok.Getter;

public enum OrderSyncStatus {
    SYNC_WAIT("等待同步"), SYNC_NO_NEED("无需同步"),SYNC_FAIL("同步失败"),SYNC_SUCCESS("同步成功");

    @Getter
    private String desc;

    OrderSyncStatus(String desc) {
        this.desc = desc;
    }
}
