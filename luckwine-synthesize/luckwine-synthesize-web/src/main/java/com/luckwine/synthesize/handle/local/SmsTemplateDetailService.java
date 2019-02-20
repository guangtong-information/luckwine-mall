package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.SmsTemplateMapper;
import com.luckwine.synthesize.model.base.SmsTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class SmsTemplateDetailService extends SingleTemplate<SmsTemplate, SmsTemplate> {

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    @Override
    protected SmsTemplate callInner(CommonRequest<SmsTemplate> request) throws Exception {

        SmsTemplate smsTemplate = request.getRequest();
        return smsTemplateMapper.selectOne(smsTemplate);

    }

}
