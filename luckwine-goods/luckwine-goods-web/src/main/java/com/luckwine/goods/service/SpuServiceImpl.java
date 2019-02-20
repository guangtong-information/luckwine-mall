package com.luckwine.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.goods.handle.local.spu.SpuDetailService;
import com.luckwine.goods.handle.local.spu.SpuPageService;
import com.luckwine.goods.handle.local.spu.SpuSaveService;
import com.luckwine.goods.model.request.spu.*;
import com.luckwine.goods.model.response.SpuDetail;
import com.luckwine.goods.model.response.SpuInfo;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuSaveService spuSaveService;

    @Autowired
    private SpuDetailService spuDetailService;

    @Autowired
    private SpuPageService spuPageService;


    @Override
    public CommonResponse<Boolean> saveSpu(CommonRequest<SpuSaveRequest> request) {
        return spuSaveService.call(request);
    }

    @Override
    public CommonResponse<Boolean> modifySpu(CommonRequest<SpuModifyRequest> request) {
        return null;
    }

    @Override
    public CommonResponse<SpuDetail> detailSpu(CommonRequest<SpuDetailRequest> request) {
        return spuDetailService.call(request);
    }

    @Override
    public CommonResponse<Boolean> modifyStatusSpu(CommonRequest<SpuStatusModifyRequest> request) {
        return null;
    }

    @Override
    public CommonQueryPageResponse<List<SpuInfo>> page(CommonQueryPageRequest<SpuPageRequest> request) {
        return spuPageService.call(request);
    }

}
