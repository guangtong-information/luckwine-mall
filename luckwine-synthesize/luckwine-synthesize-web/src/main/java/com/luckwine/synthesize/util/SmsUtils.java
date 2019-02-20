package com.luckwine.synthesize.util;

import com.luckwine.synthesize.dao.SmsTemplateMapper;
import com.luckwine.synthesize.model.base.SmsTemplate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class SmsUtils {

    @Getter
    private static HashMap<String,String> smsTemplateMap = new HashMap<String,String>();

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    /**
     * 初始化短信模板
     */
    @PostConstruct
    public void init(){
        List<SmsTemplate> smsTemplateList = smsTemplateMapper.selectAll();
        smsTemplateList.forEach(smsTemplate -> {
            smsTemplateMap.put(smsTemplate.getSmsId(),smsTemplate.getContent());
        });
        log.info("短信模板初始化{}条成功",smsTemplateList.size());
    }

}

