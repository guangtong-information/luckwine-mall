package com.luckwine.goods.model.request.category;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 *   新增分类
 */
@Data
@ToString(callSuper = true)
public class CategorySaveRequest extends BaseRequest {

    /** 分类名称 */
    @NotBlank
    private String categoryName;

}
