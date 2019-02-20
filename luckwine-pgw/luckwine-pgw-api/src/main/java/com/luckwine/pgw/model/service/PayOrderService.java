package com.luckwine.pgw.model.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.pgw.model.base.PayOrder;
import com.luckwine.pgw.model.request.PayOrderCreateRequest;
import com.luckwine.pgw.model.request.PayOrderPageRequest;
import com.luckwine.pgw.model.response.AsyncPayInfo;

import java.util.List;

public interface PayOrderService {


    /** 异步支付 创建支付单 */
    CommonResponse<AsyncPayInfo> asyncPayCreate(CommonRequest<PayOrderCreateRequest> request);

    /** 分页查询支付单 */
    CommonQueryPageResponse<List<PayOrder>> page(CommonQueryPageRequest<PayOrderPageRequest> request);

}
