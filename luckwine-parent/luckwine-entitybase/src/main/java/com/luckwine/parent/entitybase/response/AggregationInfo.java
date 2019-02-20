package com.luckwine.parent.entitybase.response;

import lombok.Data;

@Data
public class AggregationInfo extends BaseResponse {

    /** 编码 */
    private Long key;

    /** 名称 */
    private String name;

    /** 数量 */
    private Long count;

}
