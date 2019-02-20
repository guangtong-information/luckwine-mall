package com.luckwine.parent.entitybase.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通用请求父类的封装
 * 目的：封装请求的公共属性，例如模块名称等
 * @param <T>
 */
@Data
@ToString(callSuper = true)
public class CommonRequest<T> implements Serializable {

    private static final long serialVersionUID = -449603975016674678L;

    /**
     * 具体的请求对象
     */
    @Valid
    @NotNull(message = "请求对象不能为空！")
    private T request;

    /**
     * 应用模块名称
     */
    private String appName;

    /**
     * 渠道号-前端入口进来
     */
    private String channelCode;

    /**
     * 请求流水号
     */
    private String traceId;

    /**
     * 操作级别
     */
    private String operLevel;

}
