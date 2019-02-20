package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdContentQueryService extends SingleTemplate<AdContent,AdContent> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    protected AdContent callInner(CommonRequest <AdContent> request) throws Exception {
        return adContentMapper.selectByPrimaryKey(request.getRequest().getAdContentId());
    }
}
