package com.luckwine.marketing.handle.local.marketing;

import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.scheme.QuerySchemeDetailReq;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchemeDetailService extends SingleTemplate<QuerySchemeDetailReq,MarketingScheme> {

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;

    @Override
    protected MarketingScheme callInner(CommonRequest<QuerySchemeDetailReq> request) throws Exception {
        return marketingSchemeMapper.selectByPrimaryKey(request.getRequest().getSchemeId());
    }
}
