package com.luckwine.synthesize.handle.local.adDictionary;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdDictionaryMapper;
import com.luckwine.synthesize.model.base.AdDictionary;
import com.luckwine.synthesize.model.request.AdDictionaryRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdDictionaryListService extends SingleTemplate<AdDictionaryRequest,List<AdDictionary>> {

    @Resource
    private AdDictionaryMapper adDictionaryMapper;

    @Override
    protected List<AdDictionary> callInner(CommonRequest<AdDictionaryRequest> request) throws Exception
    {
        Example example = new Example(AdDictionary.class);
        Example.Criteria criteria = example.createCriteria();
        AdDictionaryRequest adDictionaryRequest = request.getRequest();
        if(StringUtils.isBlank(adDictionaryRequest.getParentId()))
            criteria.andIsNull("parentId");
        if(StringUtils.isNotBlank(adDictionaryRequest.getParentId()))
            criteria.andEqualTo("parentId",adDictionaryRequest.getParentId());
        return adDictionaryMapper.selectByExample(example);
    }
}
