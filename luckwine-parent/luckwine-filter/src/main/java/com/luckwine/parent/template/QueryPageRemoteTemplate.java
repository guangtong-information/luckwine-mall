package com.luckwine.parent.template;

import org.apache.dubbo.rpc.RpcException;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.util.ExceptionUtils;
import com.luckwine.parent.util.RequestLogUtils;
import com.luckwine.parent.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;

/**
 * 公用抽象模板
 *
 * 调用远程抽象模板，支持分页
 *
 * author:Winlone
 *
 * @param <Request>    最外层请求对象
 * @param <Response> 最外层结果对象
 */
@Slf4j
public abstract class QueryPageRemoteTemplate<Request, Response> {

    /**
     * 外层call
     * @param request
     * @return
     */
    public CommonQueryPageResponse call(CommonQueryPageRequest<Request> request) {
        CommonQueryPageResponse<Response> response = new CommonQueryPageResponse<>();
        try {
            request.setTraceId(RequestLogUtils.getCurTraceId(request));
            request.setOperLevel(RequestLogUtils.getCurOperLevel(request));
            if(!StringUtils.isEmpty(SpringContextUtil.getApplicationName())) {
                request.setAppName(SpringContextUtil.getApplicationName());
            }
            CommonQueryPageResponse<Response> resRemote = callRemote(request);
            response.setCode(resRemote.getCode());
            response.setContent(resRemote.getContent());
            if (ResponseCodeConstant.SUCCESS.getResponseCode().equals(resRemote.getCode())) {
                response.setResponse(resRemote.getResponse());
                response.setTotalCount(resRemote.getTotalCount());
                response.setTotalPage(resRemote.getTotalPage());
            }
        } catch (ConstraintViolationException e) {
            ConstraintViolationException cvException = (ConstraintViolationException)e;
            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
            response.setContent(ExceptionUtils.getMsg(cvException));
        } catch (ParamErrorException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (CommonException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (RpcException e) {
            log.error("RpcException超时,类:[{}],请求:[{}],错误类型:[{}],异常:",  this.getClass().getName(), request.toString(), e.getCode(), e);
            response.setCode(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseCode());
            response.setContent(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseDesc());
        } catch (Exception e) {
            log.error("Exception错误,类:[{}],异常:",  this.getClass().getName(), e);
            response.setCode(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
            response.setContent(ResponseCodeConstant.SYS_EXCEPTION.getResponseDesc());
        }
        return response;
    }

    /**
     * 子类模板实现：request转换 -> 请求remote -> response转换
     */
    protected abstract CommonQueryPageResponse<Response> callRemote(CommonQueryPageRequest<Request> request) throws Exception;

}
