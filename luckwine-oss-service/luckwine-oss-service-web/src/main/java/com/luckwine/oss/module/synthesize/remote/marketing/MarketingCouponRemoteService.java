package com.luckwine.oss.module.synthesize.remote.marketing;

import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.MarketingCouponReq;
import com.luckwine.marketing.service.MarketingCouponService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketingCouponRemoteService extends QueryPageRemoteTemplate<MarketingCouponReq, List<MarketingCoupon>> {

    @DubboReference(validation = "true")
    private MarketingCouponService marketingCouponService;

    @Override
    protected CommonQueryPageResponse<List<MarketingCoupon>> callRemote(CommonQueryPageRequest<MarketingCouponReq> request) throws Exception {
        return marketingCouponService.queryCouponPage(request);
    }
}
