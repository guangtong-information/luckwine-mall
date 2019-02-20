package com.luckwine.customer.service;

import com.luckwine.customer.model.base.CustCollect;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 客户商品收藏
 */
public interface CustCollectService {
    /**
     * 客户商品收藏列表
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<CustCollect>> queryCustCollectPage(CommonQueryPageRequest<CustCollect> request);

    /**
     * 添加商品收藏
     * @param request
     * @return
     */
    CommonResponse<Boolean> insertCustCollect(CommonRequest<CustCollect> request);


    /**
     * del商品收藏
     * @param request
     * @return
     */
    CommonResponse<Boolean> delCustCollect(CommonRequest<CustCollect> request);
}
