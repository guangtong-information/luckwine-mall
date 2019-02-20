package com.luckwine.goods.model.request.category;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

/** 分页查询分类 */
@Data
@ToString(callSuper = true)
public class CategoryPageRequest extends BaseRequest {

    private String categoryName;

}
