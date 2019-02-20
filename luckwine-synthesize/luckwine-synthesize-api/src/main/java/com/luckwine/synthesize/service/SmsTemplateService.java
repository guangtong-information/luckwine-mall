package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsTemplateReq;

import java.util.List;

/**
 * 短信模板管理
 */
public interface SmsTemplateService {

    /**
     * 查询短信模板详情（OSS）
     * @param request
     * @return
     */
    CommonResponse<SmsTemplate> querySmsTemplateDetail(CommonRequest<SmsTemplate> request);

    /**
     * 查询短信模板列表（OSS）
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<SmsTemplate>> querySmsTemplatePage(CommonQueryPageRequest<SmsTemplateReq> request);

    /**
     * 修改短信模板（OSS）
     * @param request
     * @return
     */
    CommonResponse<SmsTemplateReq> updateSmsTemplate(CommonRequest<SmsTemplateReq> request);

}
