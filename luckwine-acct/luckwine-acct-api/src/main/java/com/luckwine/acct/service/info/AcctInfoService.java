package com.luckwine.acct.service.info;

import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.base.ExpensesDetail;
import com.luckwine.acct.model.request.AcctExpensesPageRequest;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;

import java.util.List;

/**
 * 账户相关信息查询接口
 */
public interface AcctInfoService {

    /**
     * 分页查询账户列表
     *
     * @return
     */
    CommonQueryPageResponse<List<AcctInfo>> querylist(CommonQueryPageRequest<AcctInfoPageRequest> querySmsLogsRequest);

    /**
     * 账户资金流水查询
     * @param querySmsLogsRequest
     * @return
     */
    CommonQueryPageResponse<List<ExpensesDetail>> queryExpensesList(CommonQueryPageRequest<AcctExpensesPageRequest> querySmsLogsRequest);
}
