package com.luckwine.oss.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

public interface QueryCustomerInfoService {
	CommonResponse<CustInfo> findById(CommonRequest<CustInfo> request);

	CommonResponse<CustInfo> validRealName(CommonRequest<CustInfo> request);

}
