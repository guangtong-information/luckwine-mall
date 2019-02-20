package com.luckwine.customer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.customer.handle.local.CustInfoInsertService;
import com.luckwine.customer.handle.local.CustInfoPageService;
import com.luckwine.customer.handle.local.CustInfoQueryService;
import com.luckwine.customer.handle.local.CustInfoUpdateService;
import com.luckwine.customer.handle.local.SafrvCertCheckService;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.base.CustUpdatePassword;
import com.luckwine.customer.model.request.CustInfoUpdateReq;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.common.ValueUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log
@Service(version = "1.0.0")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustInfoPageService custInfoPageService;

	@Autowired
	private SafrvCertCheckService safrvCertCheckService;

    @Autowired
    private CustInfoInsertService custInfoEditService;

	@Autowired
	private CustInfoUpdateService custInfoUpdateService;

	@Autowired
	private CustInfoQueryService custInfoQueryService;

	@Override
	public CommonQueryPageResponse<List<CustInfo>> queryCustomerInfoPage(CommonQueryPageRequest<CustomerServiceReq> request) {
		return custInfoPageService.call(request);
	}

	/**
	 * https://market.aliyun.com/products/56844019/cmapi029454.html?spm=5176.10695662.1194487.1.1ee9118dD7abBK#sku=yuncode2345400001
	 * 云盾身份认证（二要素）
	 *
	 * @param request
	 * @return
	 */
	@Override
	public CommonResponse<Boolean> safrvCertCheck(CommonRequest<CustInfo> request) {
		return safrvCertCheckService.call(request);
	}

	/**
	 * https://market.aliyun.com/products/56844019/cmapi029847.html?spm=5176.730006-56764045-56830014-56844019-cmapi029454/A.recommend.3.7vreZF
	 * 云盾身份认证（三要素）
	 *
	 * @param request
	 * @return
	 */
	@Override
	public CommonResponse<Boolean> safrvCertPhoneCheck(CommonRequest<CustInfo> request) {

		return null;
	}

    @Override
    public CommonResponse<Boolean> insertCustomerInfo(CommonRequest<CustInfo> request) {
       return custInfoEditService.call(request);
    }

	@Override
	public CommonResponse<Boolean> updateCustomerInfo(CommonRequest<CustInfo> request) {
		return null;
	}

	@Override
	public CommonResponse<Boolean> resetPasswd(CommonRequest<CustInfo> request) {
		return null;
	}

	@Override
	public CommonResponse<Boolean> updateStatus(CommonRequest<CustInfo> request) {
		CustInfoUpdateReq updateReq = new CustInfoUpdateReq();

		List<String> columns = Arrays.asList("status");

		CustInfo custInfo = request.getRequest();

		updateReq.setCustInfo(custInfo);

		updateReq.setColumns(columns);

		CommonRequest<CustInfoUpdateReq> newRequest = new CommonRequest<>();

		newRequest.setRequest(updateReq);

		CommonResponse<CommonResponse<Boolean>> commonResponse = custInfoUpdateService.call(newRequest);


		return commonResponse.getResponse();
	}

	@Override
	public CommonResponse<CustInfo> findById(CommonRequest<CustInfo> request) {
		return custInfoQueryService.call(request);
	}

	private CommonRequest<CustInfo> initRequest(CustInfo custInfo) {
		CommonRequest<CustInfo> request = new CommonRequest<CustInfo>();

		custInfo.setUpdateTime(new Date());

		request.setRequest(custInfo);

		return request;
	}

	@Override
	public CommonResponse<Boolean> updateLoginPassword(CommonRequest<CustInfo> request) {
		CustInfoUpdateReq updateReq = new CustInfoUpdateReq();

		List<String> columns = Arrays.asList("login_pw");

		CustInfo custInfo = request.getRequest();

		updateReq.setCustInfo(custInfo);

		updateReq.setColumns(columns);

		CommonRequest<CustInfoUpdateReq> newRequest = new CommonRequest<>();

		newRequest.setRequest(updateReq);

		CommonResponse<CommonResponse<Boolean>> commonResponse = custInfoUpdateService.call(newRequest);

		return commonResponse.getResponse();
	}

	private CommonResponse<Boolean> updateLoginPasswordCustomer(CustInfo custInfo) {
		CommonRequest<CustInfo> request = initRequest(custInfo);

		CommonResponse<Boolean> response = updateLoginPassword(request);

		return response;
	}

	private CommonResponse<CustInfo> validPassword(CustUpdatePassword custUpdatePassword) {
		CommonResponse<CustInfo> response = new CommonResponse<>();

		if (!ValueUtil.equals(custUpdatePassword.getNewPassword(), custUpdatePassword.getConfigPassword())) {
			response.setCode("000033966");
			response.setContent("新的密码和确认密码不一致");

			return response;
		}

		CommonRequest<CustInfo> request = new CommonRequest<CustInfo>();

		CustInfo custInfo = new CustInfo();

		custInfo.setLoginAccount(custUpdatePassword.getLoginAccount());

		request.setRequest(custInfo);

		CommonResponse<CustInfo> queryResponse = findById(request);

		if (ValueUtil.isNotEmpty(queryResponse)) {
			custInfo = queryResponse.getResponse();

			if (!new BCryptPasswordEncoder().matches(custUpdatePassword.getCurrentPassword(), custInfo.getLoginPw())) {
				response.setCode("000033967");
				response.setContent("原密码不正确");

				return response;
			}

			if (new BCryptPasswordEncoder().matches(custUpdatePassword.getNewPassword(), custInfo.getLoginPw())) {
				response.setCode("000033968");
				response.setContent("修改密码与原密码不能相同");

				return response;
			}

			response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
			response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
			response.setResponse(custInfo);
		}

		return response;
	}

	@Override
	public CommonResponse<Boolean> optCustomerPassword(CommonRequest<CustUpdatePassword> request) {
		CommonResponse<Boolean> response = new CommonResponse<>();

		CommonResponse<CustInfo> validResponse = validPassword(request.getRequest());

		if (ValueUtil.equals(ResponseCodeConstant.SUCCESS.getResponseCode(), validResponse.getCode())) {
			response = this.updateLoginPasswordCustomer(validResponse.getResponse());
		} else {
			response.setCode(validResponse.getCode());
			response.setContent(validResponse.getContent());
			response.setResponse(false);
		}

		return response;
	}

}
