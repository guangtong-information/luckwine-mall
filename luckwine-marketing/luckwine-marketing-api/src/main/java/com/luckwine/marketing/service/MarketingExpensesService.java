package com.luckwine.marketing.service;

import com.luckwine.marketing.model.base.MarketingExpenses;
import com.luckwine.marketing.model.expenses.MarketingExpensesReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 营销交易流水
 */
public interface MarketingExpensesService {

    /**
     * 分页查询营销流水列表（LuckWine）
     *
     * @param request
     * @return
     */
    CommonQueryPageResponse<List<MarketingExpenses>> queryMarketingExpensesPage(CommonQueryPageRequest<MarketingExpensesReq> request);

}
