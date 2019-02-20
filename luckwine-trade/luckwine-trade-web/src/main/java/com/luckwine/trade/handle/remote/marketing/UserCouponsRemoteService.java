package com.luckwine.trade.handle.remote.marketing;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.marketing.model.request.coupon.UserMarketingCouponReq;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.marketing.service.MarketingCouponService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消费优惠券
 */
@Service
public class UserCouponsRemoteService extends SingleRemoteTemplate<UserMarketingCouponReq, List<UserMarketingCouponResq>> {

    @Reference(version = "1.0.0", timeout = 25000)
    private MarketingCouponService marketingCouponService;

    @Override
    protected CommonResponse<List<UserMarketingCouponResq>> callRemote(CommonRequest<UserMarketingCouponReq> remoteRequest) {
        //远程请求
        CommonResponse<List<UserMarketingCouponResq>> remoteRes = marketingCouponService.userCoupons(remoteRequest);
        return remoteRes;
    }
}
