package com.luckwine.synthesize.handle.local;


import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.synthesize.dao.AdModuleMapper;
import com.luckwine.synthesize.model.base.AdModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdModulePageService extends QueryPageTemplate<AdModule,AdModule> {

    @Resource
    private AdModuleMapper adModuleMapper;

    @Override
    protected List<AdModule> callInner(CommonQueryPageRequest<AdModule> request) throws Exception {
        Example example = new Example(AdModule.class);
        Example.Criteria criteria = example.createCriteria();
        AdModule adModule = request.getRequest();
        if(StringUtils.isNotBlank(adModule.getPageId()))
            criteria.andEqualTo("pageId",adModule.getPageId());
        if(StringUtils.isNotBlank(adModule.getSystemId()))
            criteria.andEqualTo("systemId",adModule.getSystemId());
        if(StringUtils.isNotEmpty(adModule.getName()))
            criteria.andLike("name","%"+adModule.getName()+"%");
        if(adModule.getStatus() != null && adModule.getStatus() > -1)
            criteria.andEqualTo("status",adModule.getStatus());
        return adModuleMapper.selectByExample(example);
    }
}
