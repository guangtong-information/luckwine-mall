package com.luckwine.oss.module.customer.service;

import com.luckwine.customer.model.base.CustInfo;
//import com.luckwine.customer.model.base.CustUpdatePassword;
import com.luckwine.oss.customer.service.CustomerInfoOptService;
import com.luckwine.oss.module.customer.remote.CustomerOptLoginPasswordRemoteService;
import com.luckwine.oss.module.customer.remote.CustomerUpdateLoginPasswordRemoteService;
import com.luckwine.oss.module.customer.remote.CustomerUpdateStatusRemoteService;
import com.luckwine.oss.module.customer.remote.QueryCustomerIdentityRemoteService;
import com.luckwine.oss.module.customer.remote.QueryCustomerInfoPageRemoteService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerInfoOptServiceImpl implements CustomerInfoOptService {

	@Autowired
	private CustomerUpdateStatusRemoteService customerUpdateStatusRemoteService;

	@Autowired
	private QueryCustomerInfoPageRemoteService queryCustomerInfoPageRemoteService;

	@Autowired
	private CustomerUpdateLoginPasswordRemoteService customerUpdateLoginPasswordRemoteService;

	@Autowired
	private CustomerOptLoginPasswordRemoteService customerOptLoginPasswordRemoteService;

	@Autowired
	private QueryCustomerIdentityRemoteService queryCustomerIdentityRemoteService;


	private CommonRequest<CustInfo> initRequest(CustInfo custInfo) {
		CommonRequest<CustInfo> request = new CommonRequest<CustInfo>();

		custInfo.setUpdateTime(new Date());

		request.setRequest(custInfo);

		return request;
	}

	@Override
	public CommonResponse<Boolean> updateCustomerStatus(CustInfo custInfo) {
		CommonRequest<CustInfo> request = initRequest(custInfo);

		CommonResponse<Boolean> response = customerUpdateStatusRemoteService.call(request);

		return response;
	}

	@Override
	public CommonResponse<Boolean> updateLoginPasswordCustomer(CustInfo custInfo) {
		CommonRequest<CustInfo> request = initRequest(custInfo);

		CommonResponse<Boolean> response = customerUpdateLoginPasswordRemoteService.call(request);

		return response;
	}

//	@Override
//	public CommonResponse<Boolean> optCustomerPassword(CommonRequest<CustUpdatePassword> request) {
//		CommonResponse<Boolean> response = customerOptLoginPasswordRemoteService.call(request);
//
//		return response;
//	}

	@Override
	public CommonResponse<Boolean> validCustomerIdentity(CommonRequest<CustInfo> request) {
		CommonResponse<Boolean> response = queryCustomerIdentityRemoteService.call(request);

		return response;
	}
}
