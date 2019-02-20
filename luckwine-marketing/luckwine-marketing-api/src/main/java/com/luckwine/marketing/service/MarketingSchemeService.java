package com.luckwine.marketing.service;

import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.scheme.*;
import com.luckwine.marketing.model.response.CouponCenterPageListResp;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 优惠券方案
 */
public interface MarketingSchemeService {

    /**
     * 分页查询营销方案列表（LuckWine、OSS）
     * <p>
     * 领劵中心->优惠券方案列表（LuckWine）
     * <p>
     * 营销中心->营销方案（OSS）
     *
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<CouponCenterPageListResp>> querySchemePage(CommonQueryPageRequest<CouponCenterPageListReq> request);

    /**
     * 新增营销方案（OSS）
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> saveScheme(CommonRequest<MarketingScheme> request) throws Exception;

    /**
     * 查询营销方案详情（OSS）
     *
     * @param request
     * @return
     */
    CommonResponse<MarketingScheme> querySchemeDetail(CommonRequest<QuerySchemeDetailReq> request);

    /**
     * 修改营销方案（OSS）
     * <p>
     * 备注:生成优惠券后，优惠方案不能再修改！
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> updateScheme(CommonRequest<MarketingScheme> request) throws Exception;

    /**
     * 启用/停用营销方案（OSS）
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> changeScheme(CommonRequest<ChangeSchemeReq> request);

    /**
     * 生成优惠券（OSS）
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> generateCoupons(CommonRequest<GenerateCouponsReq> request);

    /**
     * 上架、下架领劵中心（OSS）
     * <p>
     * 备注：只能单个操作
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> changeCouponCenter(CommonRequest<ChangeCouponCenterReq> request);
}
