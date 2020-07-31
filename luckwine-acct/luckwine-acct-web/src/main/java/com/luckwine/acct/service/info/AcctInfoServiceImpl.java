package com.luckwine.acct.service.info;


import com.luckwine.acct.handle.local.AcctInfoQueryPageService;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.base.ExpensesDetail;
import com.luckwine.acct.model.request.AcctExpensesPageRequest;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;


@DubboService(validation = "true")
public class AcctInfoServiceImpl implements AcctInfoService {

    @Resource
    private AcctInfoQueryPageService acctInfoQueryPageService;

    @Override
    public CommonQueryPageResponse<List<AcctInfo>> querylist(CommonQueryPageRequest<AcctInfoPageRequest> acctInfoPageRequest) {
        return acctInfoQueryPageService.call(acctInfoPageRequest);
    }

    @Override
    public CommonQueryPageResponse<List<ExpensesDetail>> queryExpensesList(CommonQueryPageRequest<AcctExpensesPageRequest> querySmsLogsRequest) {
        return null;
    }
}
