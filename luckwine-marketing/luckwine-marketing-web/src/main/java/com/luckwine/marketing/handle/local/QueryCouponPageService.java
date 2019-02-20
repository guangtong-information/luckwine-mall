package com.luckwine.marketing.handle.local;

import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.MarketingCouponReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class QueryCouponPageService extends QueryPageTemplate<MarketingCouponReq,MarketingCoupon> {

    @Resource
    private MarketingCouponMapper marketingCouponMapper;


    @Override
    protected List<MarketingCoupon> callInner(CommonQueryPageRequest<MarketingCouponReq> request) throws Exception {

//        Long count = marketingCouponMapper.queryCouponPageCount(request.getRequest());
        List<MarketingCoupon> marketingCoupons = marketingCouponMapper.queryCouponPage(request.getRequest());

        return marketingCoupons;
    }
}
