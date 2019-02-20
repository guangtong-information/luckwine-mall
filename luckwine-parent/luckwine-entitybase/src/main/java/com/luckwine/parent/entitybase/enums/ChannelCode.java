package com.luckwine.parent.entitybase.enums;

import lombok.Getter;

/**
 * 渠道号
 */
public enum ChannelCode {
    PORTALWEB("LUCKWINE-PORTAL-WEB", "酒缘网平台网站-PC版"),
    OSSWEB("LUCKWINE-OSS-WEB", "OSS运营支撑系统-PC版"),
    ;

    @Getter
    private final String code;

    @Getter
    private final String desc;

    ChannelCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
