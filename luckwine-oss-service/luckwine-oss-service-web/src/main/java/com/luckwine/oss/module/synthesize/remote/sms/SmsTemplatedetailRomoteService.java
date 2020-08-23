package com.luckwine.oss.module.synthesize.remote.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.service.SmsTemplateService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class SmsTemplatedetailRomoteService extends SingleTemplate<SmsTemplate, SmsTemplate> {

    @DubboReference(validation = "true")
    private SmsTemplateService smsTemplateService;

    @Override
    protected SmsTemplate callInner(CommonRequest<SmsTemplate> request) throws Exception {

        CommonResponse<SmsTemplate> smsTemplateCommonResponse = smsTemplateService.querySmsTemplateDetail(request);
        return smsTemplateCommonResponse.getResponse();
    }
}
