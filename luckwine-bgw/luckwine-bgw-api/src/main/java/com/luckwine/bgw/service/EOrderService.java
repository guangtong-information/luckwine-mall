package com.luckwine.bgw.service;

import com.luckwine.bgw.model.request.EOrderCreateReq;
import com.luckwine.bgw.model.request.EOrderRemoveReq;
import com.luckwine.bgw.model.request.EOrderTracesGetReq;
import com.luckwine.bgw.model.response.EOrderCreateRes;
import com.luckwine.bgw.model.response.EOrderRemoveRes;
import com.luckwine.bgw.model.response.EOrderTracesGetRes;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;

/**
 * 订单物流接口
 */
public interface EOrderService {

    /**
     * 创建电子面单
     * @param request
     * @return
     */
    CommonResponse<EOrderCreateRes> createEOrder(CommonRequest<EOrderCreateReq> request);

    /**
     * 查询物流轨迹
     * @param request
     * @return
     */
    CommonResponse<EOrderTracesGetRes> getEOrderTraces(CommonRequest<EOrderTracesGetReq> request);

    /**
     * 取消电子面单
     * @param request
     * @return
     */
    CommonResponse<EOrderRemoveRes> removeEOrder(CommonRequest<EOrderRemoveReq> request);
}
