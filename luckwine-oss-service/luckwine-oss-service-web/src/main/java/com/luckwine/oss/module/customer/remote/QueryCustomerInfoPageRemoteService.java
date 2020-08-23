package com.luckwine.oss.module.customer.remote;


import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.customer.service.CustomerService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryCustomerInfoPageRemoteService extends QueryPageRemoteTemplate<CustomerServiceReq, List<CustInfo>> {

    @DubboReference(validation = "true")
    private CustomerService customerService;

    @Override
    protected CommonQueryPageResponse<List<CustInfo>> callRemote(CommonQueryPageRequest<CustomerServiceReq> request) throws Exception {
        return customerService.queryCustomerInfoPage(request);
    }
}
