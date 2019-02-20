package com.luckwine.oss.module.marketing.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.marketing.model.request.scheme.GenerateCouponsReq;
import com.luckwine.marketing.service.MarketingSchemeService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class GenerateCouponsRemoteService extends SingleRemoteTemplate<GenerateCouponsReq, Boolean> {

    @Reference(version = "1.0.0")
    private MarketingSchemeService marketingSchemeService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<GenerateCouponsReq> request) {
        return marketingSchemeService.generateCoupons(request);
    }
}
