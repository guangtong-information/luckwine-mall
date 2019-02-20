package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Log
public class AdContentBatchDeleteService extends SingleTemplate<List<AdContent>, Boolean> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    @Transactional
    protected Boolean callInner(CommonRequest<List<AdContent>> request) throws Exception {
        System.out.println(request.getRequest().size());
        for (AdContent adContent : request.getRequest()){
            adContentMapper.deleteByPrimaryKey(adContent);
        }
        return true;
    }
}
