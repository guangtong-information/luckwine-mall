package com.luckwine.oss.module.synthesize.remote.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.service.SmsLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsLogPageRemoteService extends QueryPageRemoteTemplate<SmsLogReq, List<SmsLog>> {

    @Reference(validation = "true")
    private SmsLogService smsLogService;

    @Override
    protected CommonQueryPageResponse<List<SmsLog>> callRemote(CommonQueryPageRequest<SmsLogReq> request) throws Exception {
        return smsLogService.querySmsLogPage(request);
    }
}
