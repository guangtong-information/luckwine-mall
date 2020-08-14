package com.luckwine.bgw.service;


import com.luckwine.bgw.handle.local.EOrderCreateService;
import com.luckwine.bgw.handle.local.EOrderRemoveService;
import com.luckwine.bgw.handle.local.EOrderTracesGetService;
import com.luckwine.bgw.model.request.EOrderCreateReq;
import com.luckwine.bgw.model.request.EOrderRemoveReq;
import com.luckwine.bgw.model.request.EOrderTracesGetReq;
import com.luckwine.bgw.model.response.EOrderCreateRes;
import com.luckwine.bgw.model.response.EOrderRemoveRes;
import com.luckwine.bgw.model.response.EOrderTracesGetRes;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(validation = "true")
public class EOrderServiceImpl implements EOrderService {

    @Autowired
    private EOrderCreateService eOrderCreateRemoteService;

    @Autowired
    private EOrderTracesGetService eOrderTracesGetService;

    @Autowired
    private EOrderRemoveService eOrderRemoveService;

    @Override
    public CommonResponse<EOrderCreateRes> createEOrder(CommonRequest<EOrderCreateReq> request) {
        return eOrderCreateRemoteService.call(request);
    }

    @Override
    public CommonResponse<EOrderTracesGetRes> getEOrderTraces(CommonRequest<EOrderTracesGetReq> request) {
        return eOrderTracesGetService.call(request);
    }

    @Override
    public CommonResponse<EOrderRemoveRes> removeEOrder(CommonRequest<EOrderRemoveReq> request) {
        return eOrderRemoveService.call(request);
    }
}
