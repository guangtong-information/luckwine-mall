package com.luckwine.oss.module.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.oss.customer.service.QueryCustomerInfoPageService;
import com.luckwine.oss.module.customer.remote.QueryCustomerInfoPageRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QueryCustomerInfoPageServiceImpl implements QueryCustomerInfoPageService {

    @Autowired
    private QueryCustomerInfoPageRemoteService queryCustomerInfoPageRemoteService;

    public CommonQueryPageResponse<List<CustInfo>> findAll(CommonQueryPageRequest<CustomerServiceReq> request) {
        return queryCustomerInfoPageRemoteService.call(request);
    }

}
