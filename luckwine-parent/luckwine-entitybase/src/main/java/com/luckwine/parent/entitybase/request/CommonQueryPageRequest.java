package com.luckwine.parent.entitybase.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 带有分页功能的通用请求父类的封装
 * @param <T>
 */
@Data
@ToString(callSuper = true)
public class CommonQueryPageRequest<T> extends CommonRequest<T> {

    /**页码*/
    @NotNull(message = "页码不能为空！")
    @Min(value = 1)
    private Integer pageNo;

    /**页记录数*/
    @NotNull(message = "页数不能为空！")
    @Min(value = 1)
    private Integer pageSize;

}
