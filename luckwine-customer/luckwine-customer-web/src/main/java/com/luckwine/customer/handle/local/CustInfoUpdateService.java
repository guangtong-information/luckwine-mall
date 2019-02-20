package com.luckwine.customer.handle.local;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.luckwine.customer.dao.CustInfoMapper;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustInfoUpdateReq;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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
@Service("custInfoUpdateService")
public class CustInfoUpdateService extends SingleTemplate<CustInfoUpdateReq, CommonResponse<Boolean>> {
	@Resource
	private CustInfoMapper custInfoMapper;

	@Override
	protected CommonResponse<Boolean> callInner(CommonRequest<CustInfoUpdateReq> request) throws Exception {
		CommonResponse<Boolean> response = new CommonResponse<>();

		CustInfoUpdateReq udpateReq = request.getRequest();
		CustInfo custInfo = udpateReq.getCustInfo();
		List<String> columns = udpateReq.getColumns();
		Boolean flag = false;

		if (ValueUtil.isEmpty(custInfo) || ValueUtil.isEmpty(columns)) {
			response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
			response.setContent(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseDesc());

			response.setResponse(flag);
			return response;
		}

		Wrapper<CustInfo> condition = new UpdateWrapper<>();

//		for (String column : columns) {
//			Method method = ReflectionUtils.findMethod(CustInfo.class, "get" + ValueUtil.toUpperCaseFirstOne(column));
//
//			Object o = ReflectionUtils.invokeMethod(method, custInfo);
//
//			((UpdateWrapper<CustInfo>) condition).set(column, o);
//		}

		((UpdateWrapper<CustInfo>) condition).eq("login_account",custInfo.getLoginAccount());

		int result = custInfoMapper.update(custInfo, condition);

		if (result > 0) {
			response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
			response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
			flag = true;
		}

		response.setResponse(flag);

		return response;
	}

}
