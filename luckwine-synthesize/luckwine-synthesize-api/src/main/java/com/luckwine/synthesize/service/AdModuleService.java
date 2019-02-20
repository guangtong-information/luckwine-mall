package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.synthesize.model.base.AdModule;

import java.util.List;

/**
 * 广告模块管理
 */
public interface AdModuleService {

    /**
     * 分页查询广告模块列表（OSS）
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<AdModule>> queryAdModulePage(CommonQueryPageRequest<AdModule> request);

}
