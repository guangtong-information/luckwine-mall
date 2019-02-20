package com.luckwine.oss.module.customer.remote;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueryCustomerIdentityRemoteService extends SingleRemoteTemplate<CustInfo, Boolean> {

	@Override
	protected CommonResponse<Boolean> callRemote(CommonRequest<CustInfo> request) {
		CommonResponse<Boolean> response = new CommonResponse<>();


		return response;
	}

}
