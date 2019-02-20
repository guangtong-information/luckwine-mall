package com.luckwine.parent.entitybase.constant;


import lombok.Getter;

/**
 * AppName + ChannelCode + 错误类别 + 4位错误码 = IPRES03000000
 * 外部响应编码
 * 转换：交易成功00、交易失败01、交易处理中02、交易超时03、校验异常04   未知：系统异常05
 */
public enum ResponseCodeConstant {

    //******** 成功(00) ********
    SUCCESS("000000", "成功"),

    //******** 失败(01) ********
    FAILED_ORDER("010001", "下单失败"),

    //******** 处理中(02) ********,
    PROCESSING("020001", "交易处理中"),

    //******** 超时(03) ********
    DUBBO_UNKNOWN_EXCEPTION("030001","网络未知异常"),
    DUBBO_NETWORK_EXCEPTION("030002","网络异常"),
    DUBBO_TIME_OUT("030003","请求超时"),
    DUBBO_BIZ_EXCEPTION("030004","业务异常"),
    DUBBO_FORBIDDEN_EXCEPTION("030005","网络禁止"),
    DUBBO_SERIALIZATION_EXCEPTION("030006","网络序列化异常"),

    //******** 校验异常(04) ********
    //请求参数校验
    REQUEST_ILLEGAL("040001", "请求参数非法"),
    REQUEST_VALID("040002","请求参数合法"),

    //******** 系统异常(05) ********
    SYS_EXCEPTION("050001", "系统异常"),
    REMOTE_RESPONSE_NULL_EXCEPTION("050002", "系统异常，请确认请求是否成功再尝试发起"),
    OBJECT_CONVERT_FAILED("050003", "对象格式转换失败"),
    DB_EXCEPTION("050004", "数据库异常"),


    GOODS_STOCK_SHORTAGE("060001","库存不足");
    ;

    /**
     * 定义常量
     */
    @Getter
    private final String responseCode;

    @Getter
    private final String responseDesc;


    ResponseCodeConstant(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }


}
