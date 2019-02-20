package com.luckwine.oss.module.goods.remote.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.sku.SkuStatusModifyRequest;
import com.luckwine.goods.model.service.SkuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class SkuUpdateStatusService extends SingleRemoteTemplate<SkuStatusModifyRequest, Boolean> {


	@Reference(version = "1.0.0")
	private SkuService skuService;

	@Override
	protected CommonResponse<Boolean> callRemote(CommonRequest<SkuStatusModifyRequest> request) {
		return skuService.statusModify(request);
	}

}
