package com.luckwine.oss.module.goods.controller;

import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.goods.model.request.sku.SkuStatusModifyRequest;
import com.luckwine.goods.model.request.sku.SkuStockModifyRequest;
import com.luckwine.goods.model.request.sku.SyncSkuBySpuIdRequest;
import com.luckwine.goods.model.request.spu.SkuRequest;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.goods.remote.sku.SkuSearchRemoteService;
import com.luckwine.oss.module.goods.remote.sku.SkuSyncEsRemoteService;
import com.luckwine.oss.module.goods.remote.sku.SkuUpdateStatusService;
import com.luckwine.oss.module.goods.remote.sku.SkuUpdateStockService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "商品-sku管理")
@RequestMapping("/oss/goods/sku")
public class SkuController {


	@Autowired
	private SkuSearchRemoteService skuSearchRemoteService;

	@Autowired
	private SkuSyncEsRemoteService skuSyncEsRemoteService;

	@Autowired
	private SkuUpdateStatusService skuUpdateStatusService;

	@Autowired
	private SkuUpdateStockService skuUpdateStockService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ApiOperation(value = "sku es搜索")
	public Result<Object> search(@RequestBody CommonQueryPageRequest<SearchSkuRequest> request) {
		return new ResultUtil().setData(skuSearchRemoteService.call(request));
	}

	@RequestMapping(value = "/syncEs", method = RequestMethod.POST)
	@ApiOperation(value = "sku 同步到es")
	public Result<Object> syncEs(@RequestBody CommonRequest<SyncSkuBySpuIdRequest> request) {
		return new ResultUtil().setData(skuSyncEsRemoteService.call(request));
	}

	@RequestMapping(value = "/statusModify", method = RequestMethod.POST)
	@ApiOperation(value = "sku更新状态")
	public Result<Object> statusModify(@RequestBody CommonRequest<SkuRequest> request) {
		SkuRequest skuRequest = request.getRequest();

		CommonRequest<SkuStatusModifyRequest> requestVO = new CommonRequest<SkuStatusModifyRequest>();

		SkuStatus skuStatus = SkuStatus.valueOf(skuRequest.getStatus());

		SkuStatusModifyRequest modifyRequest = new SkuStatusModifyRequest();

		modifyRequest.setSkuStatus(skuStatus);

		modifyRequest.setId(skuRequest.getId());

		requestVO.setRequest(modifyRequest);

		return new ResultUtil().setData(skuUpdateStatusService.call(requestVO));
	}

	@RequestMapping(value = "/stockModify", method = RequestMethod.POST)
	@ApiOperation(value = "sku更新库存")
	public Result<Object> stockModify(@RequestBody CommonRequest<SkuStockModifyRequest> request) {
//		CommonRequest<SkuStockModifyRequest> requestVO = new CommonRequest<SkuStockModifyRequest>();
//
//		List<SkuStockVO> stocks = new ArrayList<>();
//
//		SkuStockVO stockVO = new SkuStockVO();
//
//		stockVO.setId(10000097L);
//
//		stockVO.setQuantity(3L);
//
//		stockVO.setStockOperate(StockOperate.DECR);
//
//		stocks.add(stockVO);
//
//		stockVO = new SkuStockVO();
//
//		stockVO.setId(10000096L);
//
//		stockVO.setQuantity(10L);
//
//		stockVO.setStockOperate(StockOperate.INCR);
//
//		stocks.add(stockVO);
//
//		SkuStockModifyRequest modifyRequest = new SkuStockModifyRequest();
//
//		modifyRequest.setStocks(stocks);
//
//		requestVO.setRequest(modifyRequest);

		return new ResultUtil().setData(skuUpdateStockService.call(request));
	}
}
