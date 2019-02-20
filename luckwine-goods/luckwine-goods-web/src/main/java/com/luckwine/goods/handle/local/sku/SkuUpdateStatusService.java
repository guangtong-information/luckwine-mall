package com.luckwine.goods.handle.local.sku;

import com.luckwine.goods.dao.SkuMapper;
import com.luckwine.goods.model.base.Sku;
import com.luckwine.goods.model.request.sku.SkuStatusModifyRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Title: sku表
 * Description: 自定义本地查询,编辑等操作方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018-9-29 10:52:15
 */
@Slf4j
@Service("skuUpdateStatusService")
public class SkuUpdateStatusService extends SingleTemplate<SkuStatusModifyRequest, Boolean> {
	@Resource
	private SkuMapper skuMapper;

	@Override
	protected Boolean callInner(CommonRequest<SkuStatusModifyRequest> request) throws Exception {
		SkuStatusModifyRequest skuVO = request.getRequest();

		if (ValueUtil.isEmpty(skuVO)) {
			log.error("");
			return false;
		}

		Sku db = new Sku();

		db.setId(skuVO.getId());
		db.setStatus(skuVO.getSkuStatus().name());
		db.setUpdateTime(new Date());

		int result = skuMapper.updateByPrimaryKeySelective(db);

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
