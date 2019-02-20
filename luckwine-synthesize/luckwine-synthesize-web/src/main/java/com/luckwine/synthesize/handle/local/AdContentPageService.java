package com.luckwine.synthesize.handle.local;


import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdContentPageService extends QueryPageTemplate<AdContent,AdContent> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    protected List<AdContent> callInner(CommonQueryPageRequest<AdContent> request) throws Exception {
        return adContentMapper.selectAll();
    }
}
