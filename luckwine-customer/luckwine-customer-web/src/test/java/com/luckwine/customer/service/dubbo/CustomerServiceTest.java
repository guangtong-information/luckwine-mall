package com.luckwine.customer.service.dubbo;


import com.luckwine.customer.CustomerApplicationTest;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.customer.service.CustomerService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class CustomerServiceTest extends CustomerApplicationTest {
    @Resource
    public CustomerService customerService;

    @Test
    public void test(){
        CommonQueryPageRequest<CustomerServiceReq> request  = new CommonQueryPageRequest<>();

        request.setPageNo(1);
        request.setPageSize(10);
		CustomerServiceReq customerServiceReq = new CustomerServiceReq();
		request.setRequest(customerServiceReq);

		CommonQueryPageResponse<List<CustInfo>> queryPageResponse = customerService.queryCustomerInfoPage(request);

		List<CustInfo> list = queryPageResponse.getResponse();

		System.out.println(list.size());

    }
}
