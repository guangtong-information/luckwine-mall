package com.luckwine.marketing.dao;

import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.MarketingCouponReq;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MarketingCouponMapper extends Mapper<MarketingCoupon> {

    /**
     * 查询优惠券列表
     * @param request
     * @return
     */
    List<MarketingCoupon> queryCouponPage(MarketingCouponReq request);

    /**
     * 查询总条数
     * @param request
     * @return
     */
    Long queryCouponPageCount(MarketRequestSeq request);
}