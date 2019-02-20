package com.luckwine.oss.module.goods.controller;

import com.luckwine.goods.model.request.props.PropsKeySaveRequest;
import com.luckwine.goods.model.request.props.PropsValueSaveRequest;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.goods.remote.props.PropsKeySaveRemoteService;
import com.luckwine.oss.module.goods.remote.props.PropsValueSaveRemoteService;
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
@Api(description = "商品-属性管理")
@RequestMapping("/oss/goods/props")
public class PropsController {
    @Autowired
    private PropsKeySaveRemoteService propsKeySaveRemoteService;

    @Autowired
    private PropsValueSaveRemoteService propsValueSaveRemoteService;

    @RequestMapping(value = "/key/save",method = RequestMethod.POST)
    @ApiOperation(value = "添加属性key")
    public Result<Object> keySave(@RequestBody CommonRequest<PropsKeySaveRequest> request){
        return new ResultUtil().setData(propsKeySaveRemoteService.call(request));
    }

    @RequestMapping(value = "/value/save",method = RequestMethod.POST)
    @ApiOperation(value = "添加属性value")
    public Result<Object> valueSave(@RequestBody CommonRequest<PropsValueSaveRequest> request){
        return new ResultUtil().setData(propsValueSaveRemoteService.call(request));
    }

}
