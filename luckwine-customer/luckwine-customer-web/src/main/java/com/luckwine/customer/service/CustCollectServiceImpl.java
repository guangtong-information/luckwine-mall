package com.luckwine.customer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.customer.handle.local.CustCollectAddService;
import com.luckwine.customer.handle.local.CustCollectDeleteService;
import com.luckwine.customer.handle.local.CustCollectPageService;
import com.luckwine.customer.model.base.CustCollect;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Log
@Service(version = "1.0.0")
public class CustCollectServiceImpl implements CustCollectService {
    @Autowired
    private CustCollectPageService custCollectPageService;

    @Autowired
    private CustCollectAddService custCollectAddService;

    @Autowired
    private CustCollectDeleteService custCollectDeleteService;

    @Override
    public CommonQueryPageResponse<List<CustCollect>> queryCustCollectPage(CommonQueryPageRequest<CustCollect> request) {
        return custCollectPageService.call(request);
    }

    @Override
    public CommonResponse<Boolean> insertCustCollect(CommonRequest<CustCollect> request) {

        return custCollectAddService.call(request);
    }

    @Override
    public CommonResponse<Boolean> delCustCollect(CommonRequest<CustCollect> request) {

        return custCollectDeleteService.call(request);
    }
}
