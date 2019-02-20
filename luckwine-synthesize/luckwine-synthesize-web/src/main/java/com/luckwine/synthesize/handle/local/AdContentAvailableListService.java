package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.enums.AdContentEnum;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdContentAvailableListService extends SingleTemplate<AdContent,List<AdContent>> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    protected List<AdContent> callInner(CommonRequest<AdContent> request) throws Exception {
        Example example = new Example(AdContent.class);
        AdContent a = new AdContent();
        //etAdModuleId
        example.createCriteria()
                .andEqualTo("adModuleId", request.getRequest().getAdModuleId())
                .andNotEqualTo("status", AdContentEnum.STATUS_2.getCode());
        return adContentMapper.selectByExample(example);
    }
}
