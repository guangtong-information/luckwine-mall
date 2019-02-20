package com.luckwine.oss.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

public interface CustomerInfoOptService {
	CommonResponse<Boolean> updateCustomerStatus(CustInfo custInfo);

	CommonResponse<Boolean> updateLoginPasswordCustomer(CustInfo custInfo);

//	CommonResponse<Boolean> optCustomerPassword(CommonRequest<CustUpdatePassword> request);

	CommonResponse<Boolean> validCustomerIdentity(CommonRequest<CustInfo> request);
}
