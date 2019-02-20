package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.SmsTemplateMapper;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SmsTemplateUpdateService extends SingleTemplate<SmsTemplateReq, SmsTemplateReq> {


    @Resource
    private SmsTemplateMapper smsTemplateMapper;


    @Override
    protected SmsTemplateReq callInner(CommonRequest<SmsTemplateReq> request) throws Exception {
        SmsTemplateReq smsTemplateReq = request.getRequest();


        if(smsTemplateReq == null || smsTemplateReq.getSmsId() == null) {
            return smsTemplateReq;
        }
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setSmsId(smsTemplateReq.getSmsId());
        smsTemplate.setSmsCode(smsTemplateReq.getSmsCode());
        smsTemplate.setContent(smsTemplateReq.getContent());
        smsTemplate.setBizName(smsTemplateReq.getBizName());
        smsTemplate.setUpdateTime(new Date());
        int count = smsTemplateMapper.updateByPrimaryKeySelective(smsTemplate);

        if(count > 0) {
            return smsTemplateReq;
        }else {
            return smsTemplateReq;
        }
    }
}
