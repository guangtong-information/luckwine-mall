package com.luckwine.oss.module.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.oss.customer.service.CustomerInfoOptService;
import com.luckwine.oss.customer.service.QueryCustomerInfoService;
import com.luckwine.oss.module.mock.base.BaseMockTest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerMockTest extends BaseMockTest {

	@Autowired
	private QueryCustomerInfoService queryCustomerInfoService;

	@Spy
	@Autowired
	private CustomerInfoOptService customerInfoOptService;

	@Test
	public void testValidRealName() {
		CommonResponse<CustInfo> response = queryCustomerInfoService.validRealName(BaseMockTest.request);


		System.out.println("下单结果：" + response);
	}

	/**
	 * 通用请求参数
	 */
	@Override
	public void initRequestInner() {
		CustInfo custInfo = new CustInfo();

		custInfo.setIdentity("350803196608054585");

		custInfo.setRealname("张三");

		//创建订单信息
		BaseMockTest.request.setRequest(custInfo);
	}

	/**
	 * mock测试
	 */
	@Override
	public void fullMockInner() {
		Mockito.doReturn(IdentityMockList.validIdentity()).when(customerInfoOptService).validCustomerIdentity(Mockito.any(CommonRequest.class));

	}
}
