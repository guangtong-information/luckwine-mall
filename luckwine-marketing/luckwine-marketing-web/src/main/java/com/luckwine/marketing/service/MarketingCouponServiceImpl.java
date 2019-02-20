package com.luckwine.marketing.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.marketing.handle.local.OrderAvailableCouponsService;
import com.luckwine.marketing.handle.local.QueryCouponPageService;
import com.luckwine.marketing.handle.local.UserCouponsService;
import com.luckwine.marketing.handle.local.marketing.MarkingReceiveCouponService;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.*;
import com.luckwine.marketing.model.response.OrderAvailableCouponResp;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(validation = "true")
public class MarketingCouponServiceImpl implements MarketingCouponService {

    @Autowired
    private OrderAvailableCouponsService orderAvailableCouponsService;

    @Autowired
    private UserCouponsService userCouponsService;
    @Autowired
    private QueryCouponPageService queryCouponPageService;
    @Autowired
    private MarkingReceiveCouponService markingReceiveCouponService;


    @Override
    public CommonResponse<List<OrderAvailableCouponResp>> orderAvailableCoupons(CommonRequest<OrderAvailableCouponReq> request) {
        return orderAvailableCouponsService.call(request);
    }

    @Override
    public CommonResponse<List<UserMarketingCouponResq>> userCoupons(CommonRequest<UserMarketingCouponReq> request) {
        return userCouponsService.call(request);
    }

    @Override
    public CommonResponse<Boolean> receiveCoupon(CommonRequest<MarketingCoupon> request) throws Exception {
        return markingReceiveCouponService.call(request);

    }

    @Override
    public CommonQueryPageResponse<List<MarketingCoupon>> queryCouponPage(CommonQueryPageRequest<MarketingCouponReq> request) {


        return queryCouponPageService.call(request);
    }

    @Override
    public CommonResponse<Boolean> activateCoupon(CommonRequest<ActivateCouponReq> request) {
        return null;
    }
}
