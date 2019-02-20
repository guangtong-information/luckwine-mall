package com.luckwine.acct.service.manage;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.luckwine.acct.handle.local.AcctOperOpenAcctService;
import com.luckwine.acct.handle.local.AcctOperfreezeAcctService;
import com.luckwine.acct.handle.local.AcctOperunfreezeAcctService;
import com.luckwine.acct.model.request.AcctFreezeBatchRequest;
import com.luckwine.acct.model.request.AcctOperRequest;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;

/**
 * 账户号管理
 */
@Service(validation = "true")
public class AcctOperServiceImpl implements AcctOperService {

    @Resource
    private AcctOperOpenAcctService acctOperOpenAcctService;

    private AcctOperfreezeAcctService acctOperfreezeAcctService;

    private AcctOperunfreezeAcctService acctOperunfreezeAcctService;

    /**
     * 账户开户
     *
     * @param request
     * @return 返回账户号
     */
    @Override
    public CommonResponse<String> openAcct(CommonRequest<AcctOperRequest> request) {
        CommonResponse<String> response = new CommonResponse<String>();
        try {
            response = acctOperOpenAcctService.call(request);
        } catch (ParamErrorException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (CommonException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (DuplicateKeyException e) {
            response.setCode(ResponseCodeConstant.DB_EXCEPTION.getResponseCode());
            response.setContent("存在不可重复数据:" + e.getCause().getMessage());
        } catch (RpcException e) {
            response.setCode(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseCode());
            response.setContent(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseDesc());
        } catch (Exception e) {
            response.setCode(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
            response.setContent(ResponseCodeConstant.SYS_EXCEPTION.getResponseDesc());
        } finally {
            return response;
        }
    }

    /**
     * 批量冻结
     *
     * @param request
     * @return
     */
    @Override
    public CommonResponse<Integer> freezeBatch(CommonRequest<AcctFreezeBatchRequest> request) {
        return acctOperfreezeAcctService.call(request);
    }

    /**
     * 批量解冻
     *
     * @param request
     * @return
     */
    @Override
    public CommonResponse<Integer> unfreezeBatch(CommonRequest<AcctFreezeBatchRequest> request) {
        return acctOperunfreezeAcctService.call(request);
    }
}
