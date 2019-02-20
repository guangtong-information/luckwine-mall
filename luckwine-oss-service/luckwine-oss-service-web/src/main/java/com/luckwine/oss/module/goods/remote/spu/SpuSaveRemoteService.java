package com.luckwine.oss.module.goods.remote.spu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpuSaveRemoteService extends SingleRemoteTemplate<SpuSaveRequest, Boolean> {

    @Reference(version = "1.0.0")
    private SpuService spuService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<SpuSaveRequest> request) {
        return spuService.saveSpu(request);
    }
}
