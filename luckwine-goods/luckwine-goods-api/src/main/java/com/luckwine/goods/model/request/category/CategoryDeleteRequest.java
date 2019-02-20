package com.luckwine.goods.model.request.category;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 *   删除分类
 */
@Data
@ToString(callSuper = true)
public class CategoryDeleteRequest extends BaseRequest {

    @NotNull
    private Long id;

}
