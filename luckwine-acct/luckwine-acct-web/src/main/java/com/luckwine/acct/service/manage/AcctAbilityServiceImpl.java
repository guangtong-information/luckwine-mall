package com.luckwine.acct.service.manage;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.luckwine.acct.handle.local.AcctAbilityBatchConfigService;
import com.luckwine.acct.model.request.AcctAbilityBatchRequest;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;


@Service(validation = "true")
public class AcctAbilityServiceImpl implements AcctAbilityService {

    @Resource
    private AcctAbilityBatchConfigService acctAbilityBatchConfigService;

    @Override
    public  CommonResponse<Integer> acctAbilityBatchConfig(CommonRequest<AcctAbilityBatchRequest> request){
        CommonResponse<Integer> response = new CommonResponse<Integer>();
        try {
            response = acctAbilityBatchConfigService.call(request);
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
