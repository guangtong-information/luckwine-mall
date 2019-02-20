package com.luckwine.customer.service;

import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 *  客户收货地址
 */
public interface CustGoodsAddrService {
    /**
     * 客户收货地址列表
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<CustGoodsAddr>> queryCustGoodsAddrPage(CommonQueryPageRequest<CustGoodsAddr> request);

    /**
     * 添加收货地址
     * @param request
     * @return
     */
    CommonResponse<Boolean> insertCustGoodsAddr(CommonRequest<CustGoodsAddr> request);

    /**
     * 修改收货地址
     * @param request
     * @return
     */
    CommonResponse<Boolean> updateCustGoodsAddr(CommonRequest<CustGoodsAddr> request);


    /**
     * del收货地址
     * @param request
     * @return
     */
    CommonResponse<Boolean> delCustGoodsAddr(CommonRequest<CustGoodsAddr> request);



}
