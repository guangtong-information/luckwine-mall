package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.model.request.SmsReq;

import java.util.List;

/**
 * 短信发送
 */
public interface SmsLogService {

    /**
     * 发送短信（LuckWine）
     * @param request
     * @return
     */
    CommonResponse<Boolean> sendSms(CommonRequest<SmsReq> request);

    /**
     * 短信发送日志列表（OSS）
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<SmsLog>> querySmsLogPage(CommonQueryPageRequest<SmsLogReq> request);

}
