package com.luckwine.synthesize.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class AdDictionaryRequest extends BaseRequest {

    private Integer id;

    // 名称
    private String name;

    // 访问路径
    private String url;

    // 排序
    private Integer sortWeight;

    // 是否激活 0.未激活 1.激活
    private Integer status;

    // 父字典编码
    private String parentId;
}
