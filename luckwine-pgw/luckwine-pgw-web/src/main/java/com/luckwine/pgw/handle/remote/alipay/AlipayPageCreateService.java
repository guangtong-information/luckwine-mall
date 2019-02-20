package com.luckwine.pgw.handle.remote.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayPageCreateService extends SingleRemoteTemplate<AlipayTradePagePayRequest, AlipayTradePagePayResponse> {
    @Autowired
    private AlipayClient alipayClient;

    @Override
    protected CommonResponse<AlipayTradePagePayResponse> callRemote(CommonRequest<AlipayTradePagePayRequest> request) {
        try {
            AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(request.getRequest());
            CommonResponse commonResponse = new CommonResponse();
            if (alipayTradePagePayResponse.isSuccess()) {
                commonResponse.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
                commonResponse.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
                commonResponse.setResponse(alipayTradePagePayResponse);
            } else {
                throw new CommonException(ResponseCodeConstant.FAILED_ORDER.getResponseCode(), alipayTradePagePayResponse.getMsg());
            }
            return commonResponse;
        } catch (AlipayApiException e) {
            throw new CommonException(ResponseCodeConstant.FAILED_ORDER.getResponseCode(), e.getMessage());
        }
    }
}
