package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.base.AdModule;
import com.luckwine.synthesize.model.request.AdContentImportReq;
import com.luckwine.synthesize.model.request.AdContentSReq;

import java.util.List;
import java.util.Map;

/**
 * 广告内容管理
 */
public interface AdContentService {

    /**
     * 根据广告模块编码，获取可用的广告内容列表（LuckWine）
     * @param request
     * @return
     */
    CommonResponse<List<AdContent>> queryAdContent(CommonRequest<AdContent> request);


    /**
     * 广告模块编码`S
     * @param request
     * @return
     */
    CommonResponse<Map<String, List<AdContent>>> queryAdContentS(CommonRequest<AdContentSReq> request);

    /**
     * 分页查询广告内容列表（OSS）
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<AdContent>> queryAdContentPage(CommonQueryPageRequest<AdContent> request);

    /**
     * 根据主键查询广告内容，用于修改（OSS）
     * @param request
     * @return
     */
    CommonResponse<AdContent> queryAdContentById(CommonRequest<AdContent> request);

    /**
     * 新增广告内容（包括导入商品）（OSS）
     * @param request
     * @return
     */
    CommonResponse<Boolean> insertAdContent(CommonRequest<AdContent> request);

    /**
     * 修改广告内容（包括上、下架）（OSS）
     * @param request
     * @return
     */
    CommonResponse<Boolean> updateAdContent(CommonRequest<AdContent> request);

    /**
     * 删除广告内容（OSS）
     * @param request
     * @return
     */
    CommonResponse<Boolean> deleteAdContent(CommonRequest<AdContent> request);

    /**
     * 批量删除广告内容（OSS）
     * @param request
     * @return
     */
    CommonResponse<Boolean> batchDeleteAdContent(CommonRequest<List<AdContent>> request);

    /**
     * 批量上架（OSS）
     * @param request
     * @return
     */
    CommonResponse<Boolean> batchShelvesAdContent(CommonRequest<List<AdContent>> request);

    /**
     * 批量下架（OSS）
     * @param request
     * @return
     */
    CommonResponse<Boolean> batchLowerFrameAdContent(CommonRequest<List<AdContent>> request);

    /**
     * 导入商品广告内容
     * @param request
     * @return
     */
    CommonResponse<Boolean> importAdContent(CommonRequest<AdContentImportReq> request);
}
