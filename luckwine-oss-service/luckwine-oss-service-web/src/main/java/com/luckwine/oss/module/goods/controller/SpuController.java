package com.luckwine.oss.module.goods.controller;

import com.luckwine.goods.model.request.spu.SpuDetailRequest;
import com.luckwine.goods.model.request.spu.SpuPageRequest;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.goods.remote.spu.SpuDetailRemoteService;
import com.luckwine.oss.module.goods.remote.spu.SpuPageRemoteService;
import com.luckwine.oss.module.goods.remote.spu.SpuSaveRemoteService;
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
@Api(description = "商品-spu管理")
@RequestMapping("/oss/goods/spu")
public class SpuController {

    @Autowired
    private SpuSaveRemoteService spuSaveRemoteService;

    @Autowired
    private SpuPageRemoteService spuPageRemoteService;

    @Autowired
    private SpuDetailRemoteService spuDetailRemoteService;


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ApiOperation(value = "添加spu")
    public Result<Object> spuSave(@RequestBody CommonRequest<SpuSaveRequest> request){
        return new ResultUtil().setData(spuSaveRemoteService.call(request));
    }


    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ApiOperation(value = "spu详情")
    public Result<Object> spuDetail(@RequestBody CommonRequest<SpuDetailRequest> request){
        return new ResultUtil().setData(spuDetailRemoteService.call(request));
    }


    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ApiOperation(value = "spu分页查询")
    public Result<Object> spuPage(@RequestBody CommonQueryPageRequest<SpuPageRequest> request){
        return new ResultUtil().setData(spuPageRemoteService.call(request));
    }

}
