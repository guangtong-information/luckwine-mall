package com.luckwine.synthesize.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sms_template")
public class SmsTemplate extends BaseRequest implements Serializable {

    @Id
    private String smsId;

    //业务名称
    private String bizName;

    //短信模板内容
    private String content;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //短信模板id
    private String smsCode;

}