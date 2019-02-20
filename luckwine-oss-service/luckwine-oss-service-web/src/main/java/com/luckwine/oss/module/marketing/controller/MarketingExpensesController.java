package com.luckwine.oss.module.marketing.controller;

import com.luckwine.marketing.model.base.MarketingExpenses;
import com.luckwine.marketing.model.expenses.MarketingExpensesReq;
import com.luckwine.marketing.model.request.scheme.CouponCenterPageListReq;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.synthesize.remote.marketing.MarketingExpensesRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
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
@Api(description = "营销-交易流水管理")
@RequestMapping("/oss/marketing/expenses")
public class MarketingExpensesController {

    @Autowired
    private MarketingExpensesRemoteService marketingExpensesRemoteService;


    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "营销方案列表 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<MarketingExpensesReq> request, @ModelAttribute MarketingExpensesReq marketingExpensesReq){
        request.setRequest(marketingExpensesReq);
        return new ResultUtil<Object>().setData(marketingExpensesRemoteService.call(request));
    }


}
