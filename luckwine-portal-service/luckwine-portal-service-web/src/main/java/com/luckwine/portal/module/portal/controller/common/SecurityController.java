package com.luckwine.portal.module.portal.controller.common;

import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HowellYang
 */
@Slf4j
@RestController
@Api(description = "Security相关接口")
@RequestMapping("/portal/common")
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public Result<Object> needLogin(){

        return new ResultUtil<Object>().setErrorMsg(401, "您还未登录");
    }
}
