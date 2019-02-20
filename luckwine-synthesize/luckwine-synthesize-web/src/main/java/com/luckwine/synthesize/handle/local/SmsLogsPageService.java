package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.synthesize.dao.SmsLogMapper;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsLogReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsLogsPageService extends QueryPageTemplate<SmsLogReq,SmsLog> {

    @Resource
    private SmsLogMapper smsLogMapper;

    @Override
    protected List<SmsLog> callInner(CommonQueryPageRequest<SmsLogReq> request) throws Exception {
        Example example = new Example(SmsLog.class);
        Example.Criteria criteria = example.createCriteria();
        //短信模板
        if (StringUtils.isNotBlank(request.getRequest().getSmsCode())) {
            criteria.andLike("smsCode",  "%"+request.getRequest().getSmsCode() + "%");
        }
        //手机号
        if (StringUtils.isNotBlank(request.getRequest().getMobile())) {
            criteria.andLike("mobile", "%" + request.getRequest().getMobile() + "%");
        }
        //手机号
        if (StringUtils.isNotBlank(request.getRequest().getResultMsg())) {
            criteria.andLike("resultMsg", "%" + request.getRequest().getResultMsg() + "%");
        }
        //创建时间
        if (request.getRequest().getCreateStartDate() != null && request.getRequest().getCreateEndDate() != null)
            criteria.andBetween("createTime", request.getRequest().getCreateStartDate(), request.getRequest().getCreateEndDate());

        return smsLogMapper.selectByExample(example);
    }
}
