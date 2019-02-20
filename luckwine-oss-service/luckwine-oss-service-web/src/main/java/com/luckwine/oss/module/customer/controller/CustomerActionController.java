package com.luckwine.oss.module.customer.controller;

import com.luckwine.customer.model.base.CustInfo;
//import com.luckwine.customer.model.base.CustUpdatePassword;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.customer.service.CustomerInfoOptService;
import com.luckwine.oss.customer.service.QueryCustomerInfoService;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "客户中心")
@RequestMapping("/oss/customer")
public class CustomerActionController {

	@Autowired
	private CustomerInfoOptService customerInfoOptService;

	@Autowired
	private QueryCustomerInfoService queryCustomerInfoService;

	@RequestMapping(value = "/action/freeze", method = RequestMethod.POST)
	@RateLimiter(limit = 1, timeout = 30)
	@ApiOperation(value = "客户中心,冻结操作")
	public Result<Object> freeze(@ModelAttribute CustInfo custInfo) {
		custInfo.setStatus(2);

		CommonResponse<Boolean> response = customerInfoOptService.updateCustomerStatus(custInfo);

		return new ResultUtil<Object>().setData(response);
	}

	@RequestMapping(value = "/action/thaw", method = RequestMethod.POST)
	@RateLimiter(limit = 1, timeout = 30)
	@ApiOperation(value = "客户中心,解冻操作")
	public Result<Object> thaw(@ModelAttribute CustInfo custInfo) {
		custInfo.setStatus(1);

		CommonResponse<Boolean> response = customerInfoOptService.updateCustomerStatus(custInfo);

		return new ResultUtil<Object>().setData(response);
	}

//	@RequestMapping(value = "/action/setLoginPasswd", method = RequestMethod.POST)
//	@RateLimiter(limit = 1, timeout = 30)
//	@ApiOperation(value = "客户中心,修改密码")
//	public Result<Object> setLoginPasswd(@ModelAttribute CustUpdatePassword custUpdatePassword) {
//		CommonRequest<CustUpdatePassword> request = new CommonRequest<CustUpdatePassword>();
//
//		request.setRequest(custUpdatePassword);
//
//		CommonResponse<Boolean> optResponse = customerInfoOptService.optCustomerPassword(request);
//
//		Result<Object> result = new ResultUtil<Object>().setData(optResponse);
//
//		if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(result.getCode())) {
//			result.setCode(300);
//			result.setMessage(optResponse.getContent());
//			result.setSuccess(false);
//		}
//
//		return result;
//	}

	@RequestMapping(value = "/action/vaildOldPassword", method = RequestMethod.POST)
	@RateLimiter(limit = 1, timeout = 30)
	@ApiOperation(value = "客户中心,验证旧密码是否正确")
	public Result<Object> vaildOldPassword(@ModelAttribute CustInfo custInfo) {
		CommonRequest<CustInfo> request = new CommonRequest<CustInfo>();

		request.setRequest(custInfo);

		CommonResponse<CustInfo> response = queryCustomerInfoService.findById(request);

		CustInfo result = response.getResponse();

		String encryptPass = new BCryptPasswordEncoder().encode(custInfo.getLoginPw());
		if (new BCryptPasswordEncoder().matches(result.getLoginPw(), encryptPass)) {

			log.info("密码正确！");
		}
		return new ResultUtil<Object>().setData(encryptPass);
	}

	@RequestMapping(value = "/action/resetLoginPasswd", method = RequestMethod.POST)
	@RateLimiter(limit = 1, timeout = 30)
	@ApiOperation(value = "客户中心,重置密码")
	public Result<Object> resetLoginPasswd(@ModelAttribute CustInfo custInfo) {
		String random = RandomStringUtils.randomNumeric(8);

		String encryptPass = new BCryptPasswordEncoder().encode(random);

		custInfo.setLoginPw(encryptPass);

		CommonResponse<Boolean> response = customerInfoOptService.updateLoginPasswordCustomer(custInfo);

		return new ResultUtil<Object>().setData(response, random);
	}

	@RequestMapping(value = "/action/setPayPasswd", method = RequestMethod.POST)
	@RateLimiter(limit = 1, timeout = 30)
	@ApiOperation(value = "客户 info page list")
	public Result<Object> setPayPasswd(@ModelAttribute CommonQueryPageRequest<CustInfo> request) {


		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/action/validRealName", method = RequestMethod.POST)
	@RateLimiter(limit = 1, timeout = 30)
	@ApiOperation(value = "客户中心,实名验证")
	public Result<Object> validRealName(@ModelAttribute CustInfo custInfo) {
		CommonRequest<CustInfo> request = new CommonRequest<CustInfo>();

		request.setRequest(custInfo);

		CommonResponse<CustInfo> response = queryCustomerInfoService.validRealName(request);

		return new ResultUtil<Object>().setData(response);
	}

}
