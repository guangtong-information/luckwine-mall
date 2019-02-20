package com.luckwine.goods.handle.local.sku;

import com.luckwine.goods.dao.SkuMapper;
import com.luckwine.goods.model.base.Sku;
import com.luckwine.goods.model.request.sku.SkuStockModifyRequest;
import com.luckwine.goods.model.request.sku.SkuStockVO;
import com.luckwine.goods.model.response.StockResponse;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
@Service("skuUpdateStockService")
public class SkuUpdateStockService extends SingleTemplate<SkuStockModifyRequest, List<StockResponse>> {
	@Resource
	private SkuMapper skuMapper;


	private StockResponse getStockResponse(Object object) {
		if (ValueUtil.isEmpty(object)) {
			StockResponse response = new StockResponse();

			response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
			response.setDesc(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseDesc());

			return response;
		}
		return null;
	}

	@Override
	protected List<StockResponse> callInner(CommonRequest<SkuStockModifyRequest> request) throws Exception {
		SkuStockModifyRequest skuVO = request.getRequest();

		List<StockResponse> response = new ArrayList<>();

		StockResponse stockResponse = getStockResponse(skuVO);

		if (ValueUtil.isNotEmpty(stockResponse)) {
			stockResponse.setId(stockResponse.getId());

			response.add(stockResponse);

			return response;
		}

		Sku db = new Sku();

		for (SkuStockVO skuStockVO : skuVO.getStocks()) {
			stockResponse = getStockResponse(skuStockVO.getStockOperate());
			if (ValueUtil.isNotEmpty(stockResponse)) {
				stockResponse.setId(skuStockVO.getId());

				response.add(stockResponse);

				continue;
			}

			db.setId(skuStockVO.getId());
			db.setQuantity(skuStockVO.getQuantity());
			db.setUpdateTime(new Date());

			int result = 0;

			String responseCode = "";
			String responseDesc = "";
			switch (skuStockVO.getStockOperate()) {
				case INCR:
					result = skuMapper.updateAddStockById(db);

					if (result > 0) {
						log.error("增加库存成功");
						responseCode = ResponseCodeConstant.SUCCESS.getResponseCode();
						responseDesc = ResponseCodeConstant.SUCCESS.getResponseDesc();
					} else {
						log.error("增加库存失败");
						responseCode = ResponseCodeConstant.FAILED_ORDER.getResponseCode();
						responseDesc = ResponseCodeConstant.FAILED_ORDER.getResponseDesc();
					}

					break;
				case DECR:
					result = skuMapper.updateDeductionStockById(db);

					if (result > 0) {
						log.error("扣减库存成功");
						responseCode = ResponseCodeConstant.SUCCESS.getResponseCode();
						responseDesc = ResponseCodeConstant.SUCCESS.getResponseDesc();
					} else {
						log.error("扣减库存失败,库存不足");
						responseCode = ResponseCodeConstant.GOODS_STOCK_SHORTAGE.getResponseCode();
						responseDesc = ResponseCodeConstant.GOODS_STOCK_SHORTAGE.getResponseDesc();
					}
					break;

				default:
					break;
			}

			stockResponse = new StockResponse();
			stockResponse.setId(skuStockVO.getId());
			stockResponse.setCode(responseCode);
			stockResponse.setDesc(responseDesc);
			response.add(stockResponse);
		}

		return response;
	}

}
