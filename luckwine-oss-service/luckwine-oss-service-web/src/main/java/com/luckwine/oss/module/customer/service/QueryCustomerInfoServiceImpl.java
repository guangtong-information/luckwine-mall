package com.luckwine.oss.module.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.oss.customer.service.CustomerInfoOptService;
import com.luckwine.oss.customer.service.QueryCustomerInfoService;
import com.luckwine.oss.module.customer.remote.QueryCustomerInfoPageRemoteService;
import com.luckwine.oss.module.customer.remote.QueryCustomerInfoRemoteService;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QueryCustomerInfoServiceImpl implements QueryCustomerInfoService {

	@Autowired
	private QueryCustomerInfoPageRemoteService queryCustomerInfoPageRemoteService;

	@Autowired
	private QueryCustomerInfoRemoteService queryCustomerInfoRemoteService;

	@Autowired
	private CustomerInfoOptService customerInfoOptService;

	public CommonQueryPageResponse<List<CustInfo>> findAll(CommonQueryPageRequest<CustomerServiceReq> request) {
		return queryCustomerInfoPageRemoteService.call(request);
	}

	@Override
	public CommonResponse<CustInfo> findById(CommonRequest<CustInfo> request) {
		return queryCustomerInfoRemoteService.call(request);
	}

	@Override
	public CommonResponse<CustInfo> validRealName(CommonRequest<CustInfo> request) {
		CustInfo custInfo = request.getRequest();
		try {
			CommonResponse<Boolean> validResponse = customerInfoOptService.validCustomerIdentity(request);

			Boolean flag = validResponse.getResponse();

			CommonResponse<CustInfo> response = new CommonResponse<>();

			if (flag) {
				response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
				response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
			} else {
//				response.setCode(ResponseCodeConstant.VILAD_FAIL.getResponseCode());
//				response.setContent(ResponseCodeConstant.VILAD_FAIL.getResponseDesc());
			}

			response.setResponse(custInfo);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
