package com.luckwine.parent.entitybase.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 请求对象的顶层父类
 *
 * 目的：防止请求对象没有序列化，导致请求异常！
 */
@Data
@ToString
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -449603975016674678L;

}

