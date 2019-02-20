package com.luckwine.oss.module.customer.remote;


import com.alibaba.dubbo.config.annotation.Reference;
//import com.luckwine.customer.model.base.CustUpdatePassword;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.customer.service.CustomerService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerOptLoginPasswordRemoteService extends SingleRemoteTemplate<CustomerServiceReq, Boolean> {
	@Override
	protected CommonResponse<Boolean> callRemote(CommonRequest<CustomerServiceReq> request) {
		return null;
	}

//	@Reference(validation = "true")
//	private CustomerService customerService;
//
//	@Override
//	protected CommonResponse<Boolean> callRemote(CommonRequest<CustUpdatePassword> request) {
//		CommonResponse<Boolean> response = customerService.optCustomerPassword(request);
//		return response;
//	}
}
