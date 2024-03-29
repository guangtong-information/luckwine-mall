package com.luckwine.portal.module.customer.controller;

import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.portal.common.annotation.RateLimiter;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.customer.service.CustomerInfoService;
import com.luckwine.portal.customer.service.QueryCustomerInfoPageService;
import com.luckwine.portal.module.customer.remote.QueryCustGoodsAddrInfoPageRemoteService;
import com.luckwine.portal.module.portal.service.GetAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(description = "客户中心")
@RequestMapping("/portal/customer")
public class CustomerInfoPageController {

    @Autowired
    private GetAuthenticationService getAuthenticationService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private QueryCustomerInfoPageService queryCustomerInfoPageService;

    @Autowired
    private QueryCustGoodsAddrInfoPageRemoteService queryCustGoodsAddrInfoPageRemoteService;


    @RequestMapping(value = "/info/page", method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "客户 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<CustomerServiceReq> request, @ModelAttribute CustomerServiceReq custInfo) {
        request.setRequest(custInfo);
        return new ResultUtil<Object>().setData(queryCustomerInfoPageService.findAll(request));
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public Result<Object> userInfo(HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);
        CustInfo custInfo = customerInfoService.findByLoginAccount(loginAccount);
        custInfo.setLoginPw("");
        custInfo.setPayPw("");
        return new ResultUtil<Object>().setData(custInfo);
    }

    /**
     * 获取用户地址
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addressList", method = RequestMethod.POST)
    public Result<Object> addressList(HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);
        CommonQueryPageRequest<CustGoodsAddr> commonQueryPageRequest = new CommonQueryPageRequest<CustGoodsAddr>();
        CustGoodsAddr custGoodsAddr = new CustGoodsAddr();
        custGoodsAddr.setLoginAccount(loginAccount);
        commonQueryPageRequest.setRequest(custGoodsAddr);
        commonQueryPageRequest.setPageNo(1);
        commonQueryPageRequest.setPageSize(10);
        return new ResultUtil<Object>().setData(queryCustGoodsAddrInfoPageRemoteService.call(commonQueryPageRequest));
    }

    /**
     * 修改收货地址
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addressUpdate", method = RequestMethod.POST)
    public Result<Object> addressUpdate(HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);

        return new ResultUtil<Object>().setData("");
    }

    /**
     * 添加收货地址
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addressAdd", method = RequestMethod.POST)
    public Result<Object> addressAdd(HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);

        return new ResultUtil<Object>().setData("");
    }

    /**
     * 删除收货地址
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addressDel", method = RequestMethod.POST)
    public Result<Object> addressDel(HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);

        return new ResultUtil<Object>().setData("");
    }


}
