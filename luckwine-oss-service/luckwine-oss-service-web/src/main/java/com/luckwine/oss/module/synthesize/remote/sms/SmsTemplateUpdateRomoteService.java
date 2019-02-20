package com.luckwine.oss.module.synthesize.remote.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import com.luckwine.synthesize.service.SmsTemplateService;
import org.springframework.stereotype.Service;

@Service
public class SmsTemplateUpdateRomoteService extends SingleTemplate<SmsTemplateReq, SmsTemplateReq> {


    @Reference(validation = "true")
    private SmsTemplateService smsTemplateService;


    @Override
    protected SmsTemplateReq callInner(CommonRequest<SmsTemplateReq> request) throws Exception {

        CommonResponse<SmsTemplateReq> response = smsTemplateService.updateSmsTemplate(request);

        return response.getResponse();
    }
}
