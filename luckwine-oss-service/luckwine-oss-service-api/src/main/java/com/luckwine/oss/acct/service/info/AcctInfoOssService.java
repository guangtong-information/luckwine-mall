package com.luckwine.oss.acct.service.info;

import com.luckwine.oss.acct.model.request.AcctInfoPageOssRequest;
import com.luckwine.oss.acct.model.response.AcctInfoOssResponse;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;

import java.util.List;

/**
 * 账户列表查询接口
 */
public interface AcctInfoOssService {

    /**
     * 分页查询账户列表
     * @return
     */
    CommonQueryPageResponse<List<AcctInfoOssResponse>> query(CommonQueryPageRequest<AcctInfoPageOssRequest> querySmsLogsRequest);

}
