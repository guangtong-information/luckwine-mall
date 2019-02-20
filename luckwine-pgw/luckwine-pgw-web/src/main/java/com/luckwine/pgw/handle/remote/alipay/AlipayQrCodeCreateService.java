package com.luckwine.pgw.handle.remote.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayQrCodeCreateService extends SingleRemoteTemplate<AlipayTradePrecreateRequest, AlipayTradePrecreateResponse> {
    @Autowired
    private AlipayClient alipayClient;

    @Override
    protected CommonResponse<AlipayTradePrecreateResponse> callRemote(CommonRequest<AlipayTradePrecreateRequest> request) {
        try {
            AlipayTradePrecreateResponse alipayTradePrecreateResponse = alipayClient.execute(request.getRequest());
            CommonResponse commonResponse = new CommonResponse();
            if (alipayTradePrecreateResponse.isSuccess()) {
                commonResponse.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
                commonResponse.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
                commonResponse.setResponse(alipayTradePrecreateResponse);
            } else {
                throw new CommonException(ResponseCodeConstant.FAILED_ORDER.getResponseCode(), alipayTradePrecreateResponse.getMsg());
            }
            return commonResponse;
        } catch (AlipayApiException e) {
            throw new CommonException(ResponseCodeConstant.FAILED_ORDER.getResponseCode(), e.getMessage());
        }
    }
}
