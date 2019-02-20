package com.luckwine.synthesize.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sms_log")
public class SmsLog extends BaseRequest{

    @Id
    private String id;

    //短信模板id
    private String smsCode;

    //手机号（用户登录号）
    private String mobile;

    //短信模板内容
    private String content;

    //短信供应商-返回响应码
    private String resultCode;

    //短信供应商-返回响应描述
    private String resultMsg;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}