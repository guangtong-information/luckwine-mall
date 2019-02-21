package com.luckwine.bgw.model.response;

import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 物流轨迹
 */
@Data
@ToString(callSuper = true)
public class ETraces extends CommonResponse {

    // 轨迹描述
    private String AcceptStation;

    //轨迹时间
    private Date AcceptTime;

}
