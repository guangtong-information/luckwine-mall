package com.luckwine.synthesize.model.request;

import com.luckwine.synthesize.model.base.SmsLog;
import lombok.Data;

/**
 * 短信发送记录查询
 */
@Data
public class SmsLogReq extends SmsLog {

    // 开始时间
    private String createStartDate;

    // 结束时间
    private String createEndDate;

}
