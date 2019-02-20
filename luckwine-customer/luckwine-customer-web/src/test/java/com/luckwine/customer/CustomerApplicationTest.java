package com.luckwine.customer;


import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.service.CustomerService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author HowellYang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class CustomerApplicationTest {

	@Resource
	private CustomerService customerService;

	@Test
	public void update() {
		CommonRequest<CustInfo> request = new CommonRequest<>();

		CustInfo vo = new CustInfo();
		vo.setLoginAccount("15817161962");
		vo.setStatus(1);

		request.setRequest(vo);

		CommonResponse<Boolean> response = customerService.updateStatus(request);

		System.out.println("udpate table record:" + response.getResponse());
	}

}
