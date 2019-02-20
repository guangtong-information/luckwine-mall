package com.luckwine.oss.module.synthesize.controller.ad;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.synthesize.service.AdDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "综合域")
@RequestMapping("/oss/addictionary")
public class AdDictionaryController {

    @Reference
    private AdDictionaryService adDictionaryService;

    @RequestMapping(value = "/ad/allSystem",method = RequestMethod.POST)
    @RateLimiter(timeout = 5000)
    @ApiOperation(value = "广告模块 所有系统")
    public Result<Object> getAllSystem()
    {
        return new ResultUtil<Object>().setData(adDictionaryService.queryAllSystem());
    }

    @RequestMapping(value = "/ad/allPage",method = RequestMethod.POST)
    @RateLimiter(timeout = 5000)
    @ApiOperation(value = "广告模块 所属页面")
    public Result<Object> getAllPage()
    {
        return new ResultUtil<Object>().setData(adDictionaryService.queryAllPage());
    }

    @RequestMapping(value = "/ad/getPageBySystmId",method = RequestMethod.POST)
    @RateLimiter(timeout = 5000)
    @ApiOperation(value = "广告模块 联动")
    public Result<Object> getPageBySystmId(CommonRequest<String> request,String systemId)
    {
        request.setRequest(systemId);
        return new ResultUtil<Object>().setData(adDictionaryService.queryPagesBySystemId(request));
    }
}
