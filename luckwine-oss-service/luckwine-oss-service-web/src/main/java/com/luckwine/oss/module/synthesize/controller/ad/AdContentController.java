package com.luckwine.oss.module.synthesize.controller.ad;

import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.annotation.SystemLog;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.synthesize.remote.ad.*;
import com.luckwine.oss.synthesize.DelAllByIdsOssRequest;
import com.luckwine.oss.module.synthesize.remote.ad.AdContentPageRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentImportReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Api(description = "综合域")
@RequestMapping("/oss/adcontent")
public class AdContentController {

    @Autowired
    private AdContentPageRemoteService adContentPageRemoteService;

    @Autowired
    private AdContentSaveRemoteService adContentSaveRemoteService;

    @Autowired
    private AdContentEditRemoteService adContentEditRemoteService;

    @Autowired
    private AdContentDeleteRemoteService adContentDeleteRemoteService;

    @Autowired
    private AdContentLowerFrameRemoteService adContentLowerFrameRemoteService;

    @Autowired
    private AdContentShelvesRemoteService adContentShelvesRemoteService;

    @Autowired
    private AdContentImportRemoteService adContentImportRemoteService;

    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "广告内容 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<AdContent> request, @ModelAttribute AdContent adContent){
        request.setRequest(adContent);
        return new ResultUtil<Object>().setData(adContentPageRemoteService.call(request));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "添加广告内容")
    public Result<Object> adContentSave(@RequestBody CommonRequest<AdContent> request) {
        return new ResultUtil<Object>().setData(adContentSaveRemoteService.call(request));
    }

    @RequestMapping(value = "/shelves", method = RequestMethod.POST)
    @ApiOperation(value = "广告内容上架")
    public Result<Object> adContentShelves(@ModelAttribute CommonRequest<List<AdContent>> request, @ModelAttribute DelAllByIdsOssRequest list) {
        List<AdContent> adContents = new ArrayList <>();
        for (String id : list.getList()){
            AdContent adContent = new AdContent();
            adContent.setAdContentId(id);
            adContents.add(adContent);
        }
        request.setRequest(adContents);
        return new ResultUtil<Object>().setData(adContentShelvesRemoteService.call(request));
    }

    @RequestMapping(value = "/lowerFrame", method = RequestMethod.POST)
    @ApiOperation(value = "广告内容下架")
    public Result<Object> adContentLowerFrame(@ModelAttribute CommonRequest<List<AdContent>> request, @ModelAttribute DelAllByIdsOssRequest list) {
        List<AdContent> adContents = new ArrayList <>();
        for (String id : list.getList()){
            AdContent adContent = new AdContent();
            adContent.setAdContentId(id);
            adContents.add(adContent);
        }
        request.setRequest(adContents);
        return new ResultUtil<Object>().setData(adContentLowerFrameRemoteService.call(request));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "查询广告内容信息")
    public Result<Object> adContentDetail(@RequestBody CommonRequest<AdContent> request) {
        return new ResultUtil<Object>().setData(adContentSaveRemoteService.call(request));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改广告内容")
    public Result<Object> adContentEdit(@RequestBody CommonRequest<AdContent> request) {
        return new ResultUtil<Object>().setData(adContentEditRemoteService.call(request));
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "批量通过ids删除广告内容")
    @SystemLog(description="批量通过ids删除广告内容")
    public Result<Object> delAllByIds(@ModelAttribute CommonRequest<List<AdContent>> request, @ModelAttribute DelAllByIdsOssRequest list){
        List<AdContent> adContents = new ArrayList <>();
        for (String id : list.getList()){
            AdContent adContent = new AdContent();
            adContent.setAdContentId(id);
            adContents.add(adContent);
        }
        request.setRequest(adContents);
        return new ResultUtil<Object>().setData(adContentDeleteRemoteService.call(request));
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ApiOperation(value = "导入广告内容")
    public Result<Object> adContentImport(@RequestBody CommonRequest<AdContentImportReq> request) {
        return new ResultUtil<Object>().setData(adContentImportRemoteService.call(request));
    }
}
