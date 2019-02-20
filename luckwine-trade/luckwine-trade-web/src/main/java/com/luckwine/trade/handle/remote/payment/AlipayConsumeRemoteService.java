package com.luckwine.trade.handle.remote.payment;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import com.luckwine.pgw.model.request.PayOrderCreateRequest;
import com.luckwine.pgw.model.response.AsyncPayInfo;
import com.luckwine.pgw.model.service.PayOrderService;
import org.springframework.stereotype.Service;

/**
 * 余额消费
 */
@Service
public class AlipayConsumeRemoteService extends SingleRemoteTemplate<PayOrderCreateRequest, AsyncPayInfo> {

    @Reference(version = "1.0.0", timeout = 25000)
    private PayOrderService payOrderService;

    @Override
    protected CommonResponse<AsyncPayInfo> callRemote(CommonRequest<PayOrderCreateRequest> remoteRequest) {
        //远程请求
        CommonResponse<AsyncPayInfo> remoteRes = payOrderService.asyncPayCreate(remoteRequest);
        return remoteRes;
    }
}
