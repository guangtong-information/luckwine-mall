package com.luckwine.synthesize.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.handle.local.SmsLogsPageService;
import com.luckwine.synthesize.handle.remote.SmsSendRemoteService;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.model.request.SmsReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 短信发送日志服务接口的实现类
 * 类似于控制层，只做服务的转发，业务逻辑的处理，放在handle中
 *
 * validation = "true"，开启服务端参数校验(默认关闭)
 */
//@Service
@Service(validation = "true")
public class SmsLogServiceImpl implements SmsLogService {

    @Autowired
    private SmsLogsPageService smsLogsPageService;

    @Autowired
    private SmsSendRemoteService smsSendRemoteService;

    @Override
    public CommonResponse<Boolean> sendSms(CommonRequest<SmsReq> request) {
        return smsSendRemoteService.call(request);
    }

    @Override
    public CommonQueryPageResponse<List<SmsLog>> querySmsLogPage(CommonQueryPageRequest<SmsLogReq> request) {
        return smsLogsPageService.call(request);
    }

}
