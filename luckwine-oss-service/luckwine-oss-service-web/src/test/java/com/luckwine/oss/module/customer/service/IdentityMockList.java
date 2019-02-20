package com.luckwine.oss.module.customer.service;

import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.common.ValueUtil;

import java.util.ArrayList;
import java.util.List;

public class IdentityMockList {

	public static CommonResponse<List<CustInfo>> custinfoList() {
		CommonResponse<List<CustInfo>> response = new CommonResponse<List<CustInfo>>();
		response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
		response.setContent("模拟" + ResponseCodeConstant.SUCCESS.getResponseDesc());

		List<CustInfo> list = new ArrayList<>(20);

//		模拟第1个身份证数据
		CustInfo vo = new CustInfo();

		vo.setIdentity("350803196608054585");

		vo.setRealname("张三");

		list.add(vo);

//		模拟第2个身份证数据
		CustInfo vo1 = new CustInfo();

		vo1.setIdentity("110101199003070097");

		vo1.setRealname("李四");

		list.add(vo1);

		response.setResponse(list);

		return response;
	}

//	public static CommonResponse<Boolean> validCustomerIdentity(CommonRequest<CustInfo> request) {
//		CommonResponse<Boolean> response = new CommonResponse<Boolean>();
//		CommonResponse<List<CustInfo>> queryResponse = custinfoList();
//		List<CustInfo> list = queryResponse.getResponse();
//		CustInfo requestVO = request.getRequest();
//
//		response.setResponse(false);
////		response.setCode(ResponseCodeConstant.VILAD_FAIL.getResponseCode());
////		response.setContent(ResponseCodeConstant.VILAD_FAIL.getResponseDesc());
//
//		for (CustInfo vo : list) {
//			if (ValueUtil.equals(vo.getRealname(), requestVO.getRealname()) && ValueUtil.equals(vo.getIdentity(), requestVO.getIdentity())) {
//				response.setResponse(true);
//				response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
//				response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
//				break;
//			}
//		}
//
//		return response;
//	}

	public static CommonResponse<Boolean> validIdentity() {
		CommonResponse<Boolean> response = new CommonResponse<Boolean>();

//		response.setResponse(false);
//		response.setCode(ResponseCodeConstant.VILAD_FAIL.getResponseCode());
//		response.setContent(ResponseCodeConstant.VILAD_FAIL.getResponseDesc());

		response.setResponse(true);
		response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
		response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());

		return response;
	}

}
