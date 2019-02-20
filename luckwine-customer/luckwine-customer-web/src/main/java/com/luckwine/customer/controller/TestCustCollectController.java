package com.luckwine.customer.controller;

import com.luckwine.customer.model.base.CustCollect;
import com.luckwine.customer.service.CustCollectService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Api(description = "测试接口")
public class TestCustCollectController {
    @Resource
    private CustCollectService custCollectService;

    @RequestMapping(value = "/test/custCollectPage",method = RequestMethod.GET)
    public CommonQueryPageResponse<List<CustCollect>> testPage(HttpServletResponse response) throws IOException {
        CommonQueryPageRequest<CustCollect> commonQueryPageRequest = new CommonQueryPageRequest<CustCollect>();
        CustCollect custCollect = new CustCollect();
        custCollect.setLoginAccount("1");
        commonQueryPageRequest.setRequest(custCollect);
        commonQueryPageRequest.setPageNo(1);
        commonQueryPageRequest.setPageSize(2);

        return custCollectService.queryCustCollectPage(commonQueryPageRequest);
    }

    @RequestMapping(value = "/test/custCollectAdd",method = RequestMethod.GET)
    public CommonResponse<Boolean> testAdd(HttpServletResponse response) throws IOException {
        CommonRequest<CustCollect> commonRequest = new CommonRequest<CustCollect>();
        CustCollect custCollect = new CustCollect();
        custCollect.setLoginAccount("1");
        custCollect.setGoodsId("3");
        commonRequest.setRequest(custCollect);
        return custCollectService.insertCustCollect(commonRequest);
    }

    @RequestMapping(value = "/test/custCollectDelete",method = RequestMethod.GET)
    public CommonResponse<Boolean> testDelete(HttpServletResponse response) throws IOException {
        CommonRequest<CustCollect> commonRequest = new CommonRequest<CustCollect>();
        CustCollect custCollect = new CustCollect();
        custCollect.setLoginAccount("1");
        custCollect.setGoodsId("1");
        commonRequest.setRequest(custCollect);
        return custCollectService.delCustCollect(commonRequest);
    }
}
