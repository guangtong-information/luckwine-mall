package com.luckwine.synthesize.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.handle.local.SmsTemplateDetailService;
import com.luckwine.synthesize.handle.local.SmsTemplatePageService;
import com.luckwine.synthesize.handle.local.SmsTemplateUpdateService;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(validation = "true")
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private SmsTemplateDetailService smsTemplateDetailService;
    @Autowired
    private SmsTemplatePageService smsTemplatePageService;
    @Autowired
    private SmsTemplateUpdateService smsTemplateUpdateService;


    @Override
    public CommonResponse<SmsTemplate> querySmsTemplateDetail(CommonRequest<SmsTemplate> request) {
        return smsTemplateDetailService.call(request);
    }

    @Override
    public CommonQueryPageResponse<List<SmsTemplate>> querySmsTemplatePage(CommonQueryPageRequest<SmsTemplateReq> request) {


        return smsTemplatePageService.call(request);
    }

    @Override
    public CommonResponse<SmsTemplateReq> updateSmsTemplate(CommonRequest<SmsTemplateReq> request) {

        CommonResponse<SmsTemplateReq> call = smsTemplateUpdateService.call(request);
        return call;
    }
}
