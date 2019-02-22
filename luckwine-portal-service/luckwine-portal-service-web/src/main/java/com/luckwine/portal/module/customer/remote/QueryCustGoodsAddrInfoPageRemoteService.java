package com.luckwine.portal.module.customer.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.customer.service.CustGoodsAddrService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryCustGoodsAddrInfoPageRemoteService  extends QueryPageRemoteTemplate<CustGoodsAddr, List<CustGoodsAddr>> {

    @Reference(validation = "true")
    private CustGoodsAddrService custGoodsAddrService;

    @Override
    protected CommonQueryPageResponse<List<CustGoodsAddr>> callRemote(CommonQueryPageRequest<CustGoodsAddr> request) throws Exception {
        return custGoodsAddrService.queryCustGoodsAddrPage(request);
    }
}
