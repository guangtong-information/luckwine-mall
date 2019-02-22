package com.luckwine.portal.module.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.portal.customer.service.CustomerInfoService;
import com.luckwine.portal.module.customer.remote.QueryCustomerInfoRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private QueryCustomerInfoRemoteService queryCustomerInfoRemoteService;

    @Override
    @Cacheable(value = "portal", key = "'user::'.concat(#loginAccount.toString())")
    public CustInfo findByLoginAccount(String loginAccount) {
        // 模拟登陆数据
        CustInfo custInfo = new CustInfo();
        custInfo.setLoginAccount(loginAccount);
//        custInfo.setStatus(1); //"客户状态：1.正常 2.冻结", 状态为1:才能正常登陆
//        custInfo.setLoginAccount("15817161961");
//        custInfo.setLoginPw(new BCryptPasswordEncoder().encode("123456"));
//        custInfo.setStatus(0);
//        //todo 调用 customer 模块的dubbo接口

        CommonRequest<CustInfo> commonRequest = new CommonRequest();
        commonRequest.setRequest(custInfo);
        CommonResponse<CustInfo> custInfoCommonResponse = queryCustomerInfoRemoteService.call(commonRequest);
        return custInfoCommonResponse.getResponse();
    }


    @CacheEvict(value = "portal", key = "'user'.concat(#loginAccount.toString())")
    public void delCache(String loginAccount) {

    }
}
