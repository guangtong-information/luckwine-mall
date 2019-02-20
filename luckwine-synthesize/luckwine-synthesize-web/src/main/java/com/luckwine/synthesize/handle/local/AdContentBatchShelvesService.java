package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Log
public class AdContentBatchShelvesService extends SingleTemplate<List<AdContent>, Boolean> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    @Transactional
    protected Boolean callInner(CommonRequest<List<AdContent>> request) throws Exception {
        System.out.println(request.getRequest().size());
        for (AdContent adContent : request.getRequest()){
            adContent = adContentMapper.selectByPrimaryKey(adContent);
            Date timeStart = adContent.getTimeStart();
            Date timeEnd = adContent.getTimeEnd();
            Date newTime = new Date();
            if(timeStart.getTime()<newTime.getTime()&&timeEnd.getTime()>newTime.getTime()){
                adContent.setStatus(1);
                adContentMapper.updateByPrimaryKey(adContent);
            }
        }
        return true;
    }
}
