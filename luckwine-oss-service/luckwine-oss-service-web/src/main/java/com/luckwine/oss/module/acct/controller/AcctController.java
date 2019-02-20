package com.luckwine.oss.module.acct.controller;

import com.luckwine.oss.acct.model.request.AcctAbilityBatchOssRequest;
import com.luckwine.oss.acct.model.request.AcctInfoPageOssRequest;
import com.luckwine.oss.acct.service.info.AcctInfoOssService;
import com.luckwine.oss.acct.service.manage.AcctAbilityOssService;
import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地址命名规范：info-查询类, manage-有操作数据库的管理配置类, trans-交易类
 */
@Slf4j
@RestController
@Api(description = "余额账户系统接口")
@RequestMapping("/oss/acct")
public class AcctController {

    @Autowired
    private AcctInfoOssService acctInfoOssService;

    @Autowired
    private AcctAbilityOssService acctAbilityOssService;

    @RequestMapping(value = "/info/queryList", method = RequestMethod.POST)
    @ApiOperation(value = "余额账户查询列表")
    public Result<Object> query(@ModelAttribute CommonQueryPageRequest<AcctInfoPageOssRequest> request, @ModelAttribute AcctInfoPageOssRequest acctInfoPageOssRequest) {
        request.setRequest(acctInfoPageOssRequest);
        return new ResultUtil<Object>().setData(acctInfoOssService.query(request));
    }

    @RequestMapping(value = "/manage/acctAbilityBatchConfig", method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 5000)
    @ApiOperation(value = "余额账户能力配置")
    public Result<Object> query(@ModelAttribute CommonRequest<AcctAbilityBatchOssRequest> request, @ModelAttribute AcctAbilityBatchOssRequest acctAbilityBatchOssRequest) {
        request.setRequest(acctAbilityBatchOssRequest);
        return new ResultUtil<Object>().setData(acctAbilityOssService.acctAbilityBatchConfig(request));
    }
}
