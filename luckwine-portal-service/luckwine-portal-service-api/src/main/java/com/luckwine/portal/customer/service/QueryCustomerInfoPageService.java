package com.luckwine.portal.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;

import java.util.List;

public interface QueryCustomerInfoPageService {
    CommonQueryPageResponse<List<CustInfo>> findAll(CommonQueryPageRequest<CustomerServiceReq> request);
}
