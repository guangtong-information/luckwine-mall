package com.luckwine.parent.entitybase.response;

import lombok.Data;
import lombok.ToString;

/**
 * 带有分页功能的通用响应父类的封装
 * 目的：封装响应的公共属性，例如响应编码等
 * @param <T>
 */
@ToString(callSuper = true)
@Data
public class CommonQueryPageResponse<T> extends CommonResponse<T> {

    /**
     * totalCount 总条数
     */
    private long totalCount;

    /**
     * totalPage 总页数
     */
    private int totalPage;

}
