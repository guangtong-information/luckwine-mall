package com.luckwine.oss.module.synthesize.remote.marketing;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.marketing.model.base.MarketingExpenses;
import com.luckwine.marketing.model.expenses.MarketingExpensesReq;
import com.luckwine.marketing.service.MarketingExpensesService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.QueryPageRemoteTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarketingExpensesRemoteService extends QueryPageRemoteTemplate <MarketingExpensesReq,List<MarketingExpenses>> {

    @Reference(validation = "true")
    private MarketingExpensesService marketingExpensesService;

    @Override
    protected CommonQueryPageResponse<List<MarketingExpenses>> callRemote(CommonQueryPageRequest<MarketingExpensesReq> request) throws Exception {
        return marketingExpensesService.queryMarketingExpensesPage(request);
    }
}
