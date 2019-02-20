package com.luckwine.synthesize.model.request.enums;

import lombok.Getter;

public enum SmsCodeEnum {

    TEMPLATE_01("1", "用户注册验证码", "验证码"),

    TEMPLATE_02("2", "账户付款验证码", "验证码"),
    ;

    @Getter
    private String code;

    @Getter
    private String bizName;

    @Getter
    private String msg;

    SmsCodeEnum(String code, String bizName, String msg) {
        this.code = code;
        this.bizName = bizName;
        this.msg = msg;
    }

}
