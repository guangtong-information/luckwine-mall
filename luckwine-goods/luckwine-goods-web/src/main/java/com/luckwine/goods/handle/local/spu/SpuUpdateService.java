package com.luckwine.goods.handle.local.spu;

import com.luckwine.goods.dao.SpuMapper;
import com.luckwine.goods.model.base.Spu;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.entitybase.transform.AbstractExchanger;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Service("custInfoUpdateService")
public class SpuUpdateService extends SingleTemplate<SpuSaveRequest, CommonResponse<Spu>> {

	@Resource
	private SpuMapper spuMapper;

	@Autowired
	@Qualifier("spuExchanger")
	private AbstractExchanger<SpuSaveRequest, Spu> spuExchanger;

	@Override
	protected CommonResponse<Spu> callInner(CommonRequest<SpuSaveRequest> request) throws Exception {
		CommonResponse<Spu> response = new CommonResponse<>();

		SpuSaveRequest spuVO = request.getRequest();

		if (ValueUtil.isEmpty(spuVO)) {
			response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
			response.setContent(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseDesc());
		}

		Spu spu = spuExchanger.voToDb(spuVO);

		int result = spuMapper.updateByPrimaryKeySelective(spu);

		if (result > 0) {
			response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
			response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
		}

		response.setResponse(spu);

		return response;
	}

}
