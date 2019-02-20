package com.luckwine.customer.handle.local;

import com.luckwine.customer.dao.CustInfoMapper;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Title: 客户信息表
 * Description: 自定义本地查询,编辑等操作方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018-9-20 10:11:39
 */
@Slf4j
@Service("custInfoDeleteService")
public class CustInfoDeleteService extends SingleTemplate<CustInfo, CommonResponse<CustInfo>> {
	@Resource
	private CustInfoMapper custInfoMapper;

	@Override
	protected CommonResponse<CustInfo> callInner(CommonRequest<CustInfo> request) throws Exception {
		CommonResponse<CustInfo> response = new CommonResponse<>();

		CustInfo custInfo = request.getRequest();

		if (ValueUtil.isEmpty(custInfo)) {
			response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
			response.setContent(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseDesc());
		}

		int result = custInfoMapper.deleteById(custInfo);

		if (result > 0) {
			response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
			response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
		}

		response.setResponse(custInfo);

		return response;
	}
}
