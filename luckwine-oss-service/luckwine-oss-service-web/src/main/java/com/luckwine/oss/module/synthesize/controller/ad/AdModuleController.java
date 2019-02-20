package com.luckwine.oss.module.synthesize.controller.ad;

import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.synthesize.remote.ad.AdModulePageRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.synthesize.model.base.AdModule;
import com.luckwine.synthesize.model.request.SmsLogReq;
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
@Api(description = "综合域")
@RequestMapping("/oss/admodule")
public class AdModuleController {

    @Autowired
    private AdModulePageRemoteService adModulePageRemoteService;

    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "广告模块 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<AdModule> request, @ModelAttribute AdModule adModule){
        request.setRequest(adModule);
        return new ResultUtil<Object>().setData(adModulePageRemoteService.call(request));
    }

}
