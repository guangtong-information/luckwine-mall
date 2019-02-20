package com.luckwine.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.base.CustUpdatePassword;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 客户中心
 */
public interface CustomerService {

    /**
     * 客户信息分页查询
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<CustInfo>> queryCustomerInfoPage(CommonQueryPageRequest<CustomerServiceReq> request);


    /**
     * 开户 / 注册  todo:(acct 模块 开户dubbo接口) AcctOperService.openAcct()
     * @param request
     * @return
     */
    CommonResponse<Boolean> insertCustomerInfo(CommonRequest<CustInfo> request);

    /**
     * 身份二要素实名制认证（姓名+身份证号码）
     * @param request
     * @return
     */
    CommonResponse<Boolean> safrvCertCheck(CommonRequest<CustInfo> request);


    /**
     * 身份三要素实名制认证（手机号+姓名+身份证号）
     * @param request
     * @return
     */
    CommonResponse<Boolean> safrvCertPhoneCheck(CommonRequest<CustInfo> request);

    /**
     * 更新
     * @param request
     * @return
     */
    CommonResponse<Boolean> updateCustomerInfo(CommonRequest<CustInfo> request);

    /**
     * 重置密码
     * @param request
     * @return
     */
    CommonResponse<Boolean> resetPasswd(CommonRequest<CustInfo> request);


    /**
     * 更新客户状态：1.正常 2.冻结
     * @param request
     * @return
     */
    CommonResponse<Boolean>updateStatus(CommonRequest<CustInfo> request);

	/**
	 * 根据主键,查找对应的客户信息
	 * @param request
	 * @return
	 */
	CommonResponse<CustInfo>findById(CommonRequest<CustInfo> request);

	/**
	 * 重置密码,生成随机数
	 * @param request
	 * @return
	 */
	CommonResponse<Boolean> updateLoginPassword(CommonRequest<CustInfo> request);

	/**
	 * 修改用户密码
	 * @param request
	 * @return
	 */
	CommonResponse<Boolean> optCustomerPassword(CommonRequest<CustUpdatePassword> request);
}
