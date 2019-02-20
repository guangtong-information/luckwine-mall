package com.luckwine.oss.module.oss.controller.common;


import com.luckwine.oss.common.utils.IpInfoUtil;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HowellYang
 */
@Slf4j
@RestController
@Api(description = "IP接口")
@RequestMapping("/oss/common/ip")
public class IpInfoController {

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "IP及天气相关信息")
    public Result<Object> upload(HttpServletRequest request) {

        String result= IpInfoUtil.getIpWeatherInfo(IpInfoUtil.getIpAddr(request));
        return new ResultUtil<Object>().setData(result);
    }
}