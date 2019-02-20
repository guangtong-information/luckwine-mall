package com.luckwine.marketing.handle.local.marketing;

import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.DataBaseTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MarketingSchemeSaveService extends DataBaseTemplate<MarketingScheme,Boolean> {

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;

    @Override
    protected Boolean callInner(CommonRequest<MarketingScheme> request) throws Exception {
        MarketingScheme marketingScheme = request.getRequest();
        int row = marketingSchemeMapper.insert(marketingScheme);
        if(row>0)
            return true;
        else
            return false;
    }
}
