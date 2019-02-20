package com.luckwine.oss.module.customer.controller;

import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.customer.service.QueryCustomerInfoPageService;
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
@Api(description = "客户中心")
@RequestMapping("/oss/customer")
public class CustomerInfoPageController {

    @Autowired
    private QueryCustomerInfoPageService queryCustomerInfoPageService;

    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "客户 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<CustomerServiceReq> request, @ModelAttribute CustomerServiceReq custInfo){
        request.setRequest(custInfo);
        return new ResultUtil<Object>().setData(queryCustomerInfoPageService.findAll(request));
    }
}
