package com.luckwine.oss.module.synthesize.controller.sms;

import com.luckwine.oss.common.annotation.RateLimiter;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.synthesize.remote.sms.SmsTemplatePageRomoteService;
import com.luckwine.oss.module.synthesize.remote.sms.SmsTemplateUpdateRomoteService;
import com.luckwine.oss.module.synthesize.remote.sms.SmsTemplatedetailRomoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.synthesize.model.base.SmsTemplate;
import com.luckwine.synthesize.model.request.SmsTemplateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(description = "综合域")
@RequestMapping("/oss/smstemplate")
public class SmsTemplateController {

    @Autowired
    private SmsTemplatePageRomoteService smsTemplatePageRomoteService;

    @Autowired
    private SmsTemplatedetailRomoteService smsTemplatedetailRomoteService;
    @Autowired
    private SmsTemplateUpdateRomoteService smsTemplateUpdateRomoteService;

    @RequestMapping(value = "/info/page",method = RequestMethod.POST)
    @RateLimiter(limit = 1, timeout = 6000)
    @ApiOperation(value = "短信模板列表 info page list")
    public Result<Object> infoPage(@ModelAttribute CommonQueryPageRequest<SmsTemplateReq> request, @ModelAttribute SmsTemplateReq smsTemplateReq){
        request.setRequest(smsTemplateReq);
        return new ResultUtil<Object>().setData(smsTemplatePageRomoteService.call(request));
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ApiOperation(value = "模板详情")
    public Result<Object> smsTemplateDetail(@RequestBody CommonRequest<SmsTemplate> request){
        return new ResultUtil().setData(smsTemplatedetailRomoteService.call(request));
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Result<Object> updateSmsTemplate(@ModelAttribute CommonRequest<SmsTemplateReq> request, @ModelAttribute SmsTemplateReq smsTemplate ){
        request.setRequest(smsTemplate);

        return new ResultUtil().setData(smsTemplateUpdateRomoteService.call(request));
    }







}
