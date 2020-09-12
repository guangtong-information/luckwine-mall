package com.luckwine.portal.module.customer.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.service.CustomerService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class QueryCustomerInfoRemoteService extends SingleRemoteTemplate<CustInfo, CustInfo> {
    //@Reference(validation = "true",  url = "dubbo://127.0.0.1:28032")
    @DubboReference(validation = "true")
    private CustomerService customerService;

    @Override
    protected CommonResponse<CustInfo> callRemote(CommonRequest<CustInfo> request) {
        return customerService.findById(request);
    }
}
