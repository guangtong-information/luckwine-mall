package com.luckwine.acct.service.trans;


import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcException;
import com.luckwine.acct.enums.AbilityCode;
import com.luckwine.acct.handle.local.AcctDeductingService;
import com.luckwine.acct.model.request.AcctDeductingRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;

/**
 * 账户出账-提现
 */
@DubboService(validation = "true")
public class AccountDeductingServiceImpl implements AccountDeductingService {

    @Resource
    private AcctDeductingService acctDeductingService;

    /**
     * 账户出账接口-提现
     *
     * @param request
     * @return 账户流水号
     */
    @Override
    public CommonResponse<TransRes> withdraw(CommonRequest<AcctDeductingRequest> request) {
        CommonResponse<TransRes> response = new CommonResponse<TransRes>();
        try {
            request.getRequest().setAbilityCode(AbilityCode.WITHDRAW);
            response = acctDeductingService.call(request);
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
     * 账户出账接口-消费
     *
     * @param request
     * @return 账户流水号
     */
    @Override
    public CommonResponse<TransRes> consume(CommonRequest<AcctDeductingRequest> request) {
        CommonResponse<TransRes> response = new CommonResponse<TransRes>();
        try {
            request.getRequest().setAbilityCode(AbilityCode.CONSUME);
            response = acctDeductingService.call(request);
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
