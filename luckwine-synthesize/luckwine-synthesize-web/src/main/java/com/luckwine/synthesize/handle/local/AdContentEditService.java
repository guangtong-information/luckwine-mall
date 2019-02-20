package com.luckwine.synthesize.handle.local;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
@Log
public class AdContentEditService extends SingleTemplate<AdContent, Boolean> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    protected Boolean callInner(CommonRequest<AdContent> request) throws Exception {
        AdContent adContent = request.getRequest();
        adContent.setOperatingTime(new Date());
        Integer row = adContentMapper.updateByPrimaryKeySelective(adContent);
        if (row == 1) {
            return true;
        } else {
            return false;
        }
    }
}
