package com.luckwine.acct.controller;

import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.acct.service.info.AcctInfoService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private AcctInfoService acctInfoService;

    @RequestMapping(value = "/local", method = RequestMethod.GET)
    public  CommonQueryPageResponse<List<AcctInfo>> testLocal(HttpServletResponse httpServletResponse) {
        CommonQueryPageRequest<AcctInfoPageRequest> request = new CommonQueryPageRequest<AcctInfoPageRequest>();
        request.setTraceId(SequenceUtil.genSequence(SequenceCode.TRACEID, "1"));
        request.setPageSize(10);
        request.setPageNo(1);
        AcctInfoPageRequest acctInfoPageRequest=new AcctInfoPageRequest();
        acctInfoPageRequest.setCreateTimeStart("2018-09-04 00:00:00");
        acctInfoPageRequest.setCreateTimeEnd("2018-09-04 23:59:59");
        request.setRequest(acctInfoPageRequest);

        CommonQueryPageResponse<List<AcctInfo>> response = acctInfoService.querylist(request);

        System.out.println("账户列表：" + response);

        return response;
    }

}
