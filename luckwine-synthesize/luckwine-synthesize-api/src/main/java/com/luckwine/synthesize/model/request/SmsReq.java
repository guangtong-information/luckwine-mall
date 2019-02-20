package com.luckwine.synthesize.model.request;

import com.luckwine.parent.entitybase.response.BaseResponse;
import com.luckwine.synthesize.model.request.enums.SmsCodeEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 发送短信
 */
@Data
public class SmsReq extends BaseResponse {

    /**
     * 短信模板编码
     */
    @NotNull
    private SmsCodeEnum smsCodeEnum;

    /**
     * 手机号
     */
    @NotNull
    private String mobile;

    /**
     * 短信占位符内容，|分隔
     */
    @NotNull
    private String content;

}
