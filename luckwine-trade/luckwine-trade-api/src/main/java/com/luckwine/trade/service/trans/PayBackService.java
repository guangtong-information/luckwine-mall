package com.luckwine.trade.service.trans;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.model.request.PayBackRequest;

/**
 * 支付回调
 * Created by Winlone on 2018/9/20.
 */
public interface PayBackService {

    /**
     * 支付回调
     * @param request
     * @return
     */
    CommonResponse<Boolean> payBack(CommonRequest<PayBackRequest> request);
}
