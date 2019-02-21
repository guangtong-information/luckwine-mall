package com.luckwine.bgw.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class EOrderTracesGetRes extends CommonResponse {

    @JSONField(name = "Traces")
    private List<ETraces> eTracesList;

    // 快递单号
    private String logisticCode;

    // 快递公司编码
    private String shipperCode;

    // 物流状态：0-无轨迹 1-已揽收 2-在途中 3-签收 4-问题件
    private String state;

    //成功与否(true/false)
    private String Success;

}
