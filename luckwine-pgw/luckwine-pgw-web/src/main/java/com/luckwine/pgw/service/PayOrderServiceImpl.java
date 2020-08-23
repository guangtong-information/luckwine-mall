package com.luckwine.pgw.service;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.pgw.handle.local.payorder.PayOrderAlipayPageCreateService;
import com.luckwine.pgw.handle.local.payorder.PayOrderAlipayQrCodeCreateService;
import com.luckwine.pgw.model.base.PayOrder;
import com.luckwine.pgw.model.request.PayOrderCreateRequest;
import com.luckwine.pgw.model.request.PayOrderPageRequest;
import com.luckwine.pgw.model.response.AsyncPayInfo;
import com.luckwine.pgw.model.service.PayOrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PayOrderServiceImpl implements PayOrderService {

    @Autowired
    private PayOrderAlipayPageCreateService payOrderAlipayPageCreateService;

    @Autowired
    private PayOrderAlipayQrCodeCreateService payOrderAlipayQrCodeCreateService;

    @Override
    public CommonResponse<AsyncPayInfo> asyncPayCreate(CommonRequest<PayOrderCreateRequest> request) {
        switch (request.getRequest().getPayType()) {
            case ALIPAY_PAGE: return payOrderAlipayPageCreateService.callInner(request);
            case ALIPAY_QR_CODE: return payOrderAlipayQrCodeCreateService.callInner(request);
        }
        CommonResponse commonResponse  = new CommonResponse();
        commonResponse.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
        commonResponse.setContent("暂不支持此方式");
        return commonResponse;
    }

    @Override
    public CommonQueryPageResponse<List<PayOrder>> page(CommonQueryPageRequest<PayOrderPageRequest> request) {
        return null;
    }
}
