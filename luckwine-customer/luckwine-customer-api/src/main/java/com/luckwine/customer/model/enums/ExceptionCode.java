package com.luckwine.customer.model.enums;



public enum ExceptionCode {
    not_login_account("000001","login_account不能为空"),
    has_account("000002","用户已存在"),
    not_account("000003","用户未注册");



    private final String code;
    private final String message;

    ExceptionCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
