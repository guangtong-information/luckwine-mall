package com.luckwine.pgw.model.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.pgw.model.base.PayOrderDiff;
import com.luckwine.pgw.model.base.PayOrderDiffBatch;
import com.luckwine.pgw.model.request.PayOrderDiffBatchPageRequest;
import com.luckwine.pgw.model.request.PayOrderDiffPageRequest;

import java.util.List;

public interface PayOrderDiffService {

    /** 分页查询差异数据 */
    CommonQueryPageResponse<List<PayOrderDiff>> page(CommonQueryPageRequest<PayOrderDiffPageRequest> request);

    /** 分页查询差异数据批次 */
    CommonQueryPageResponse<List<PayOrderDiffBatch>> pageBatch(CommonQueryPageRequest<PayOrderDiffBatchPageRequest> request);
}
