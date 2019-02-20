package com.luckwine.goods.model.request.category;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *   修改分类
 */
@Data
@ToString(callSuper = true)
public class CategoryModifyRequest extends BaseRequest {

    @NotNull
    private Long id;

    /** 分类名称 */
    @NotBlank
    private String categoryName;

}
