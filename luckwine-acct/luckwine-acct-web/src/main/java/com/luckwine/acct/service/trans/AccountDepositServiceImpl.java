package com.luckwine.acct.service.trans;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.luckwine.acct.handle.local.AcctDepositService;
import com.luckwine.acct.model.request.AcctDepositRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;

/**
 * 账户入账
 */
@Service(validation = "true")
public class AccountDepositServiceImpl implements AccountDepositService {


    @Resource
    private AcctDepositService acctDepositService;
    /**
     * 账户入账接口
     *
     * @param request
     * @return 账户流水号
     */
    @Override
    public CommonResponse<TransRes> deposit(CommonRequest<AcctDepositRequest> request) {
        CommonResponse<TransRes> response = new CommonResponse<TransRes>();
        try {
            response = acctDepositService.call(request);
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

}


