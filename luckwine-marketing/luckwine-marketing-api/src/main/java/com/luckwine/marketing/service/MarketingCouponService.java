package com.luckwine.marketing.service;

import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.*;
import com.luckwine.marketing.model.response.OrderAvailableCouponResp;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 优惠券
 */
public interface MarketingCouponService {

    /**
     * 查询订单可用优惠券（LuckWine）
     * <p>
     * 备注：
     * （1）一个商品只能使用一张优惠券，选择优惠金额最大
     * （2）多个商品可以共用一张优惠券，选择优惠金额最大
     * （3）一个订单可以使用多张优惠券
     *
     * @param request 用户ID号、商品SKUID集合
     * @return 优惠券集合（包含商品信息，其中：优惠券：商品=1：N）
     */
    CommonResponse<List<OrderAvailableCouponResp>> orderAvailableCoupons(CommonRequest<OrderAvailableCouponReq> request);

    /**
     * 使用优惠券（LuckWine）
     * <p>
     * 备注：
     * （1）第一步：调用getOrderAvailableMarketingCoupons接口，验证优惠劵是否可用
     * （2）第二步：使用优惠券
     * （3）第三步：生成营销流水
     * （4）一个商品只能使用一张优惠券、多个商品可以共用一张优惠券、一个订单可以使用多张优惠券
     *
     * @param request 用户ID号、订单号、订单资金流水集合（包含商品信息，其中：订单资金流水：优惠券：商品=1:1:N）
     * @return 营销流水集合（包含商品信息，其中：营销流水：商品=1：N）
     */
    CommonResponse<List<UserMarketingCouponResq>> userCoupons(CommonRequest<UserMarketingCouponReq> request);

    /**
     * 线上领取优惠券（LuckWine）
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> receiveCoupon(CommonRequest<MarketingCoupon> request) throws Exception;

    /**
     * 分页查询优惠券列表（LuckWine、OSS）
     * <p>
     * 个人中心->我的优惠券（LuckWine）
     * <p>
     * 营销方案->优惠券列表（OSS）
     *
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<MarketingCoupon>> queryCouponPage(CommonQueryPageRequest<MarketingCouponReq> request);

    /**
     * 激活、发放优惠券（LuckWine、OSS）
     * <p>
     * 个人中心->我的优惠券（LuckWine）
     * <p>
     * 营销方案->优惠券列表（OSS）
     *
     * @param request
     * @return
     */
    CommonResponse<Boolean> activateCoupon(CommonRequest<ActivateCouponReq> request);
}
