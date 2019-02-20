package com.luckwine.oss.module.marketing.controller;

import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.scheme.CouponCenterPageListReq;
import com.luckwine.marketing.model.request.scheme.GenerateCouponsReq;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.marketing.remote.GenerateCouponsRemoteService;
import com.luckwine.oss.module.synthesize.remote.marketing.MarketingQuerySchemeRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(description = "营销-方案管理")
@RequestMapping("/oss/marketing/scheme")
public class MarketingSchemeController {

    @Autowired
    private GenerateCouponsRemoteService generateCouponsRemoteService;
    @Autowired
    private MarketingQuerySchemeRemoteService marketingQuerySchemeRemoteService;

    @RequestMapping(value = "/generatecoupons", method = RequestMethod.POST)
    @ApiOperation(value = "生产优惠券")
    public Result<Object> generateCoupons(@RequestBody CommonRequest<GenerateCouponsReq> request) {
        return new ResultUtil<>().setData(generateCouponsRemoteService.call(request));
    }

    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "营销方案列表 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<CouponCenterPageListReq> request, @ModelAttribute CouponCenterPageListReq marketRequestSeq){
        request.setRequest(marketRequestSeq);
        return new ResultUtil<Object>().setData(marketingQuerySchemeRemoteService.call(request));
    }

}
