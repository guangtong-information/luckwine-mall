package com.luckwine.portal.module.synthesize.controller;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.module.synthesize.remote.QueryAdContentRemoteService;
import com.luckwine.portal.module.synthesize.remote.QueryAdContentSRemoteService;
import com.luckwine.portal.module.synthesize.service.AdContentInfoService;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentSReq;
import com.luckwine.synthesize.model.request.enums.AdContentEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(description = "综合域 广告管理")
@RequestMapping("/portal/ad")
public class AdContentActionController {

    @Autowired
    private QueryAdContentRemoteService queryAdContentRemoteService;

    @Autowired
    private QueryAdContentSRemoteService queryAdContentSRemoteService;

    @Autowired
    private AdContentInfoService adContentInfoService;

    @RequestMapping(value = "/queryAdContent", method = RequestMethod.POST)
    @ApiOperation(value = " 获取可用的广告内容列表")
    public Result<Object> queryAdContent(@ModelAttribute CommonRequest<AdContent> request , @ModelAttribute AdContent searchSkuRequest) {
        request.setRequest(searchSkuRequest);
        return new ResultUtil<Object>().setData(queryAdContentRemoteService.call(request));
    }

    @RequestMapping(value = "/queryAdContentS", method = RequestMethod.POST)
    @ApiOperation(value = " 获取可用的广告内容列表")
    public Result<Object> queryAdContentS(@ModelAttribute CommonRequest<AdContentSReq> request , @ModelAttribute AdContentSReq searchSkuRequest) {
        searchSkuRequest.setAdContentEnum(AdContentEnum.STATUS_1);
        request.setRequest(searchSkuRequest);
        return new ResultUtil<Object>().setData(adContentInfoService.getAdContent(request,searchSkuRequest.getPageId()));
    }

    @RequestMapping(value = "/delAdContent/{pageId}", method = RequestMethod.GET)
    @ApiOperation(value = " 获取可用的广告内容列表")
    public Result<Object> delAdContent(@PathVariable("pageId") String pageId) {
        return new ResultUtil<Object>().setData(adContentInfoService.delCache(pageId));
    }

}
