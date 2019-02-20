package com.luckwine.oss.module.goods.controller;

import com.luckwine.goods.model.request.brand.BrandPageRequest;
import com.luckwine.goods.model.request.brand.BrandSaveRequest;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.goods.remote.brand.BrandPageRemoteService;
import com.luckwine.oss.module.goods.remote.brand.BrandSaveRemoteService;
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
@Api(description = "商品-品牌管理")
@RequestMapping("/oss/goods/brand")
public class BrandController {

    @Autowired
    private BrandSaveRemoteService brandSaveRemoteService;

    @Autowired
    private BrandPageRemoteService brandPageRemoteService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ApiOperation(value = "新增品牌")
    public Result<Object> brandSave(@RequestBody CommonRequest<BrandSaveRequest> request){
        return new ResultUtil().setData(brandSaveRemoteService.call(request));
    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ApiOperation(value = "分页查询品牌")
    public Result<Object> brandPage(@RequestBody CommonQueryPageRequest<BrandPageRequest> request){
        return new ResultUtil().setData(brandPageRemoteService.call(request));
    }

}
