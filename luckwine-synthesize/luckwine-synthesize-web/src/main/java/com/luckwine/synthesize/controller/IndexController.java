package com.luckwine.synthesize.controller;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.service.AdContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class IndexController {

    @Autowired
    AdContentService adContentService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/actuator/info");
    }

    @RequestMapping(value = "/adContentList", method = RequestMethod.GET)
    public ResponseEntity adContentList(@RequestParam("adModuleId") String adModuleId) {
        CommonRequest<AdContent> commonRequest = new CommonRequest();
        AdContent adContent = new AdContent();
        adContent.setAdModuleId(adModuleId);
        commonRequest.setRequest(adContent);

        CommonResponse<List<AdContent>> commonResponse = adContentService.queryAdContent(commonRequest);

        return ResponseEntity.badRequest().body(commonResponse);

    }

}
