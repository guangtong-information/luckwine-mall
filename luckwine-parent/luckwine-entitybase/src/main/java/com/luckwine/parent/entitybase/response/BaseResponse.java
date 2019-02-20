package com.luckwine.parent.entitybase.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应对象的顶层父类
 *
 * 目的：防止响应对象没有序列化，导致请求异常！
 */
@Data
@ToString
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -4496039752344554678L;

}
