package com.luckwine.goods.model.service;

import com.luckwine.goods.model.request.spu.*;
import com.luckwine.goods.model.response.SpuDetail;
import com.luckwine.goods.model.response.SpuInfo;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

public interface SpuService {

    /** 新增一个spu */
    CommonResponse<Boolean> saveSpu(CommonRequest<SpuSaveRequest> request);

    /* 修改spu */
    CommonResponse<Boolean> modifySpu(CommonRequest<SpuModifyRequest> request);

    /** 获取spu详情 */
    CommonResponse<SpuDetail> detailSpu(CommonRequest<SpuDetailRequest> request);

    /*  spu状态变更  */
    CommonResponse<Boolean> modifyStatusSpu(CommonRequest<SpuStatusModifyRequest> request);

    /* 分页查询spu */
    CommonQueryPageResponse<List<SpuInfo>> page(CommonQueryPageRequest<SpuPageRequest> request);

}
