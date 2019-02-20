package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.synthesize.SynthesizeApplication;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.model.request.SmsReq;
import com.luckwine.synthesize.model.request.enums.SmsCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
public class SmsLogServiceTest {

    @Resource
    private SmsLogService smsLogService;

    @Test
    public void testQuerySmsLogs(){

        CommonQueryPageRequest<SmsLogReq> querySmsLogsPageRequest = new CommonQueryPageRequest<>();

        SmsLogReq querySmsLogsReq = new SmsLogReq();
        querySmsLogsReq.setMobile("13326465600");
        querySmsLogsReq.setCreateStartDate("20181010");
        querySmsLogsReq.setCreateEndDate("20181110");

        querySmsLogsPageRequest.setRequest(querySmsLogsReq);
        querySmsLogsPageRequest.setPageSize(1);
        querySmsLogsPageRequest.setPageNo(1);

        CommonQueryPageResponse<List<SmsLog>> response = smsLogService.querySmsLogPage(querySmsLogsPageRequest);

        System.out.println(response);

    }

    @Test
    public void testSendSms() {

        CommonRequest<SmsReq> commonRequest = new CommonRequest<>();
        SmsReq smsReq = new SmsReq();
        smsReq.setSmsCodeEnum(SmsCodeEnum.TEMPLATE_01);
        smsReq.setMobile("13326465600");
        smsReq.setContent("8888");
        commonRequest.setRequest(smsReq);

        Assert.assertEquals(smsLogService.sendSms(commonRequest).getResponse(),true);;
    }

}
