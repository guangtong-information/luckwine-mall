package com.luckwine.synthesize.service;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.luckwine.synthesize.handle.remote.SmsSendByAliyunRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SmsSendByAliyunRemoteServiceTest {

    private SmsSendByAliyunRemoteService smsSendByAliyunRemoteService = new SmsSendByAliyunRemoteService();

    @Test
    public void testSendSms() throws ClientException, InterruptedException{

        //发短信
        SendSmsResponse response = smsSendByAliyunRemoteService.sendSms();

        log.info("短信接口返回的数据----------------");
        log.info("Code=" + response.getCode());
        log.info("Message=" + response.getMessage());
        log.info("RequestId=" + response.getRequestId());
        log.info("BizId=" + response.getBizId());

    }

    @Test
    public void testQuerySendDetails() throws ClientException{
        QuerySendDetailsResponse querySendDetailsResponse = smsSendByAliyunRemoteService.querySendDetails("13326465600",null);
        log.info("短信明细查询接口返回数据----------------");
        log.info("Code=" + querySendDetailsResponse.getCode());
        log.info("Message=" + querySendDetailsResponse.getMessage());
        int i = 0;
        for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs()) {
            log.info("SmsSendDetailDTO[" + i + "]:");
            log.info("Content=" + smsSendDetailDTO.getContent());
            log.info("ErrCode=" + smsSendDetailDTO.getErrCode());
            log.info("OutId=" + smsSendDetailDTO.getOutId());
            log.info("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
            log.info("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
            log.info("SendDate=" + smsSendDetailDTO.getSendDate());
            log.info("SendStatus=" + smsSendDetailDTO.getSendStatus());
            log.info("Template=" + smsSendDetailDTO.getTemplateCode());
        }
        log.info("TotalCount=" + querySendDetailsResponse.getTotalCount());
        log.info("RequestId=" + querySendDetailsResponse.getRequestId());
    }

}
