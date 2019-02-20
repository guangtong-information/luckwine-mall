package com.luckwine.oss.module.marketing.controller;

import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.MarketingCouponReq;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.synthesize.remote.marketing.MarketingCouponRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "营销-优惠券管理")
@RequestMapping("/oss/marketing/coupon")
public class MarketingCouponController {

    @Autowired
    private MarketingCouponRemoteService marketingCouponRemoteService;

    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "优惠券列表 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<MarketingCouponReq> request, @ModelAttribute MarketingCouponReq marketingCouponReq){
        request.setRequest(marketingCouponReq);
        return new ResultUtil<Object>().setData(marketingCouponRemoteService.call(request));
    }
}
