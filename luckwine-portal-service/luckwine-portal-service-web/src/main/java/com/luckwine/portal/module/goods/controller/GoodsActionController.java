package com.luckwine.portal.module.goods.controller;

import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.goods.model.request.spu.SpuDetailRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.module.goods.remote.QueryGoodsDetailRemoteService;
import com.luckwine.portal.module.goods.remote.QueryGoodsPageRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "客户中心")
@RequestMapping("/portal/goods")
public class GoodsActionController {

    @Autowired
    private QueryGoodsPageRemoteService queryGoodsPageRemoteService;

    @Autowired
    private QueryGoodsDetailRemoteService queryGoodsDetailRemoteService;


    @RequestMapping(value = "/computer", method = RequestMethod.POST)
    @ApiOperation(value = " info page list")
    public Result<Object> computer(@ModelAttribute CommonQueryPageRequest<SearchSkuRequest> request, @ModelAttribute SearchSkuRequest searchSkuRequest) {
        searchSkuRequest.setSpuStatus(SpuStatus.ON_SALE);
        searchSkuRequest.setSkuStatus(SkuStatus.ON_SALE);
        request.setRequest(searchSkuRequest);
        return new ResultUtil<Object>().setData(queryGoodsPageRemoteService.call(request));
    }


    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = " detail info ")
    public Result<Object> detail(@ModelAttribute CommonRequest<SpuDetailRequest> request, @ModelAttribute SpuDetailRequest spuDetailRequest) {
        request.setRequest(spuDetailRequest);
        return new ResultUtil<Object>().setData(queryGoodsDetailRemoteService.call(request));
    }


}
