package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.SynthesizeApplication;
import com.luckwine.synthesize.handle.local.SmsTemplatePageService;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SynthesizeApplication.class)
@WebAppConfiguration
public class SmsTemplateServiceTest {

    @Resource
    private SmsTemplateService smsTemplateService;



    @Test
    public void testSmsTemplateDetail() {

        CommonRequest<SmsTemplate> smsTemplateDetailRequest = new CommonRequest<>();

        SmsTemplate smsTemplate = new SmsTemplate();

        smsTemplate.setSmsCode("2");

        smsTemplateDetailRequest.setRequest(smsTemplate);

        CommonResponse<SmsTemplate> responseCommonResponse = smsTemplateService.querySmsTemplateDetail(smsTemplateDetailRequest);

        System.out.println(responseCommonResponse);

    }

    @Test
    public void testquerySmsTemplatePage() {
        CommonQueryPageRequest<SmsTemplateReq> request = new CommonQueryPageRequest<>();

        SmsTemplateReq smsTemplate = new SmsTemplateReq();


        smsTemplate.setBizName("1");

        request.setRequest(smsTemplate);
        request.setPageSize(1);
        request.setPageNo(1);
        CommonQueryPageResponse<List<SmsTemplate>> responseCommonResponse = smsTemplateService.querySmsTemplatePage(request);

        System.out.println("responseCommonResponse:" + responseCommonResponse);

    }

}
