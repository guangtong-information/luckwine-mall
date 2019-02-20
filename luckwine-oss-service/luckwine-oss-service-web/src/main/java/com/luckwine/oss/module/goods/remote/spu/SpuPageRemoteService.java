package com.luckwine.oss.module.goods.remote.spu;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.spu.SpuPageRequest;
import com.luckwine.goods.model.response.SpuInfo;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuPageRemoteService  extends QueryPageRemoteTemplate<SpuPageRequest, List<SpuInfo>> {

    @Reference(version = "1.0.0")
    private SpuService spuService;

    @Override
    protected CommonQueryPageResponse<List<SpuInfo>> callRemote(CommonQueryPageRequest<SpuPageRequest> request) throws Exception {
        return spuService.page(request);
    }
}
