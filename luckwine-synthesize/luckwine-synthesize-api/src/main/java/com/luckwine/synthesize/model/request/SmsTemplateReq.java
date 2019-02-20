package com.luckwine.synthesize.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import com.luckwine.synthesize.model.base.SmsTemplate;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板请求参数
 */
@Data
@ToString(callSuper = true)
public class SmsTemplateReq extends BaseRequest implements Serializable {

    private String smsId;

    //业务名称
    private String bizName;

    //短信模板内容
    private String content;
    //短信模板cde
    private String smsCode;

    /**
     * 开始时间
     */
    private String createTimeStart;

    /**
     * 结束时间
     */
    private String createTimeEnd;





}
