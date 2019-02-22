package com.luckwine.portal.module.customer.controller;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.portal.common.annotation.RateLimiter;
import com.luckwine.portal.common.constant.SecurityConstant;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.customer.service.CustomerInfoService;
import com.luckwine.portal.module.customer.remote.InsertCustomerInfoRemoteService;
import com.luckwine.portal.module.portal.service.GetAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@Api(description = "客户中心")
@RequestMapping("/portal/customer")
public class CustomerActionController {


    @Autowired
    private GetAuthenticationService getAuthenticationService;

    @Autowired
    private InsertCustomerInfoRemoteService insertCustomerInfoRemoteService;


    @Autowired
    private CustomerInfoService customerInfoService;

    @RequestMapping(value = "/action/setLoginPasswd", method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "客户 info page list")
    public Result<Object> setLoginPasswd(@ModelAttribute CustInfo request) {

        String encryptPass = new BCryptPasswordEncoder().encode(request.getLoginPw());
        if (new BCryptPasswordEncoder().matches(request.getLoginPw(), encryptPass)) {
            log.info("密码正确！");
        }
        return new ResultUtil<Object>().setData(encryptPass);
    }

    @RequestMapping(value = "/action/setPayPasswd", method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "客户 info page list")
    public Result<Object> setPayPasswd(@ModelAttribute CommonQueryPageRequest<CustInfo> request) {


        return new ResultUtil<Object>().setData(null);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<Object> register(@ModelAttribute CommonRequest<CustInfo> request, @ModelAttribute CustInfo custInfo) {
        String encryptPass = new BCryptPasswordEncoder().encode(custInfo.getLoginPw());
        custInfo.setLoginPw(encryptPass);
        custInfo.setNickname(custInfo.getLoginAccount());
        custInfo.setStatus(1);
        custInfo.setCustLevel(1);
        custInfo.setHeaderimg("https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png");
        request.setRequest(custInfo);
        CommonResponse<Boolean> custInfoCommonResponse = insertCustomerInfoRemoteService.call(request);
        if (!custInfoCommonResponse.getCode().equals("000000")) {
            return new ResultUtil<Object>().setErrorMsg(custInfoCommonResponse.getContent());
        }
        return new ResultUtil<Object>().setData(custInfoCommonResponse);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Result<Object> loginOut(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie(SecurityConstant.HEADER, "");
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        String loginAccount = getAuthenticationService.getUsernameByToken(request);
        customerInfoService.delCache(loginAccount);
        return new ResultUtil<Object>().setData("ok");
    }


}
