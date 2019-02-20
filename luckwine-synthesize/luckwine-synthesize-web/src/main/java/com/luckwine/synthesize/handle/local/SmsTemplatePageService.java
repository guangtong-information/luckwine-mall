package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.SmsTemplateMapper;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsLogReq;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsTemplatePageService extends QueryPageTemplate<SmsTemplateReq , SmsTemplate> {
//    QueryPageTemplate<SmsLogReq,SmsLog>
//    SingleTemplate<SmsTemplate, SmsTemplate>
    @Resource
    private SmsTemplateMapper smsTemplateMapper;

//    @Override
//    protected SmsTemplate callInner(CommonRequest<SmsTemplate> request) throws Exception {
//
//        SmsTemplate smsTemplate = request.getRequest();
//        String smsId =  smsTemplate.getSmsId();
//
//        return smsTemplateMapper.selectByPrimaryKey(smsId);
//
//    }

    @Override
    protected List<SmsTemplate> callInner(CommonQueryPageRequest<SmsTemplateReq> request) throws Exception {

        Example example = new Example(SmsTemplate.class);
        Example.Criteria criteria = example.createCriteria();
        //短信模板
        if (StringUtils.isNotBlank(request.getRequest().getSmsCode())) {
            criteria.andLike("smsCode",  "%"+request.getRequest().getSmsCode() + "%");
        }
        //业务名称
        if (StringUtils.isNotBlank(request.getRequest().getBizName())) {
            criteria.andLike("bizName", "%" + request.getRequest().getBizName() + "%");
        }
        //创建时间
        if (request.getRequest().getCreateTimeStart() != null && request.getRequest().getCreateTimeEnd() != null)
            criteria.andBetween("createTime", request.getRequest().getCreateTimeStart(), request.getRequest().getCreateTimeEnd());


        return smsTemplateMapper.selectByExample(example);
    }


}
