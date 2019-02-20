package com.luckwine.marketing.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.marketing.handle.local.marketing.MarketingQuerySchemePageService;
import com.luckwine.marketing.handle.local.marketing.MarketingSchemeSaveService;
import com.luckwine.marketing.handle.local.marketing.MarketingSchemeUpdateService;
import com.luckwine.marketing.handle.local.marketing.SchemeDetailService;
import com.luckwine.marketing.handle.local.GenerateCouponsService;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.scheme.*;
import com.luckwine.marketing.model.response.CouponCenterPageListResp;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@Service(validation = "true")
public class MarketingSchemeServiceImpl implements MarketingSchemeService {

    @Resource
    private MarketingSchemeSaveService marketingSchemeSaveService;
    @Resource
    private MarketingSchemeUpdateService marketingSchemeUpdateService;
    @Resource
    private SchemeDetailService schemeDetailService;
    @Resource
    private MarketingQuerySchemePageService marketingQuerySchemePageService;


    @Resource
    private GenerateCouponsService generateCouponsService;

    @Override
    public CommonQueryPageResponse<List<CouponCenterPageListResp>> querySchemePage(CommonQueryPageRequest<CouponCenterPageListReq> request) {


        return marketingQuerySchemePageService.call(request);
    }

    @Override
    public CommonResponse<Boolean> saveScheme(CommonRequest<MarketingScheme> request) throws Exception{
        return marketingSchemeSaveService.call(request);
    }

    @Override
    public CommonResponse<MarketingScheme> querySchemeDetail(CommonRequest<QuerySchemeDetailReq> request) {
        return schemeDetailService.call(request);
    }

    @Override
    public CommonResponse<Boolean> updateScheme(CommonRequest<MarketingScheme> request) throws Exception{
        return marketingSchemeUpdateService.call(request);
    }

    @Override
    public CommonResponse<Boolean> changeScheme(CommonRequest<ChangeSchemeReq> request) {
        return null;
    }

    @Override
    public CommonResponse<Boolean> generateCoupons(CommonRequest<GenerateCouponsReq> request) {
        return generateCouponsService.call(request);
    }

    @Override
    public CommonResponse<Boolean> changeCouponCenter(CommonRequest<ChangeCouponCenterReq> request) {
        return null;
    }
}
