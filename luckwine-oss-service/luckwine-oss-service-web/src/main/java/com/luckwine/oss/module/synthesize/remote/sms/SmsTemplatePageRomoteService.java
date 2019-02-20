package com.luckwine.oss.module.synthesize.remote.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.model.request.SmsReq;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import com.luckwine.synthesize.service.SmsLogService;
import com.luckwine.synthesize.service.SmsTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SmsTemplatePageRomoteService extends QueryPageRemoteTemplate<SmsTemplateReq, List<SmsTemplate>> {

    @Reference(validation = "true")
    private SmsTemplateService smsTemplateService;


    @Override
    protected CommonQueryPageResponse<List<SmsTemplate>> callRemote(CommonQueryPageRequest<SmsTemplateReq> request) throws Exception {
        CommonQueryPageResponse<List<SmsTemplate>> response = smsTemplateService.querySmsTemplatePage(request);
        return response;
    }




}
