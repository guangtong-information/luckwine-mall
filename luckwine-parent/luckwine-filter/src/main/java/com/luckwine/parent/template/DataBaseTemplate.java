package com.luckwine.parent.template;


import org.apache.dubbo.rpc.RpcException;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.util.ExceptionUtils;
import com.luckwine.parent.util.RequestLogUtils;
import com.luckwine.parent.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;

/**
 * 数据库事务抽象模板
 * author:Winlone
 *
 * @param <Request>  最外层请求对象
 * @param <Response> 最外层结果对象
 */
@Slf4j
public abstract class DataBaseTemplate<Request, Response> {
    /**
     * 外层call
     *
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 5000, rollbackFor = Exception.class)
    public CommonResponse<Response> call(CommonRequest<Request> request) throws Exception {
        CommonResponse<Response> response = new CommonResponse<Response>();
        try {
            if (!StringUtils.isEmpty(SpringContextUtil.getApplicationName())) {
                request.setAppName(SpringContextUtil.getApplicationName());
            }
            request.setTraceId(RequestLogUtils.getCurTraceId(request));
            request.setOperLevel(RequestLogUtils.getCurOperLevel(request));
            //请求remote
            Response Response = callInner(request);
            response.setResponse(Response);
            response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
        } catch (ConstraintViolationException e) {
            log.error("ConstraintViolationException错误,类:[{}],异常:", this.getClass().getName(), e);
            throw e;
        } catch (ParamErrorException e) {
            log.error("ParamErrorException错误,类:[{}],异常:", this.getClass().getName(), e);
            throw e;
        } catch (CommonException e) {
            log.error("CommonException错误,类:[{}],异常:", this.getClass().getName(), e);
            throw e;
        } catch (DuplicateKeyException e) {
            log.error("DuplicateKeyException错误,类:[{}],异常:", this.getClass().getName(), e);
            throw e;
        } catch (RpcException e) {
            log.error("RpcException超时,类:[{}],请求:[{}],错误类型:[{}],异常:", this.getClass().getName(), request.toString(), e.getCode(), e);
            throw e;
        } catch (Exception e) {
            log.error("Exception错误,类:[{}],异常:", this.getClass().getName(), e);
            throw e;
        }
        return response;
    }

    /**
     * 子类模板实现：request转换 -> 请求remote -> response转换
     */
    protected abstract Response callInner(CommonRequest<Request> request) throws Exception;
}
