package com.luckwine.pgw.model.enums;

import lombok.Getter;

public enum PayType {
    ALIPAY_QR_CODE("支付宝二维码"),
    WECHAT_QR_CODE("微信二维码"),
    ALIPAY_PAGE("支付宝网页支付"),
    ONLINE_BANK("网银");

    PayType(String desc) {
        this.desc = desc;
    }

    @Getter
    private String desc;
}
