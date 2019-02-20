package com.luckwine.marketing.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.luckwine.marketing.handle.local.marketing.MarketingExpensesPageService;
import com.luckwine.marketing.model.base.MarketingExpenses;
import com.luckwine.marketing.model.expenses.MarketingExpensesReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;

import javax.annotation.Resource;
import java.util.List;

@Service(validation = "true")
public class MarketingExpensesServiceImpl implements MarketingExpensesService{

    @Resource
    private MarketingExpensesPageService marketingExpensesPageService;

    @Override
    public CommonQueryPageResponse<List<MarketingExpenses>> queryMarketingExpensesPage(CommonQueryPageRequest<MarketingExpensesReq> request) {
        return marketingExpensesPageService.call(request);
    }

}
