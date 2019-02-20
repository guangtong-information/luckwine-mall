package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.model.base.AdDictionary;
import com.luckwine.synthesize.model.request.AdDictionaryRequest;

import java.util.List;

/**
 * 广告字典管理
 */
public interface AdDictionaryService {

    /**
     * 查询所有的系统（OSS）
     * @return
     */
    CommonResponse<List<AdDictionary>> queryAllSystem();

    /**
     * 查询所有的页面（OSS）
     * @return
     */
    CommonResponse<List<AdDictionary>> queryAllPage();

    /**
     * 根据系统，查询所有的页面（联动效果）（OSS）
     * @param request
     * @return
     */
    CommonResponse<List<AdDictionary>> queryPagesBySystemId(CommonRequest<String> request);
}
