package com.luckwine.oss.module.synthesize.remote.marketing;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.marketing.model.request.scheme.CouponCenterPageListReq;
import com.luckwine.marketing.model.response.CouponCenterPageListResp;
import com.luckwine.marketing.service.MarketingSchemeService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketingQuerySchemeRemoteService  extends QueryPageRemoteTemplate<CouponCenterPageListReq, List<CouponCenterPageListResp>> {

    @DubboReference(validation = "true")
    private MarketingSchemeService marketingSchemeService;


    @Override
    protected CommonQueryPageResponse<List<CouponCenterPageListResp>> callRemote(CommonQueryPageRequest<CouponCenterPageListReq> request) throws Exception {
        return marketingSchemeService.querySchemePage(request);
    }
}
