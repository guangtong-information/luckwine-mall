package com.luckwine.parent.template;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.util.ExceptionUtils;
import com.luckwine.parent.util.RequestLogUtils;
import com.luckwine.parent.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;

/**
 * 公用抽象模板
 *
 * 调用Dao层抽象模板，不支持分页
 *
 * author:Winlone
 *
 * @param <Request> 最外层请求对象
 * @param <Response> 最外层结果对象
 */
@Slf4j
public abstract class SingleTemplate<Request, Response> {

    /**
     * 外层call
     *
     * @param request
     * @return
     */
    public CommonResponse<Response> call(CommonRequest<Request> request) {
        CommonResponse<Response> response = new CommonResponse<Response>();
        try {
            if(!StringUtils.isEmpty(SpringContextUtil.getApplicationName())) {
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
            ConstraintViolationException cvException = (ConstraintViolationException)e;
            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
            response.setContent(ExceptionUtils.getMsg(cvException));
        }  catch (ParamErrorException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (CommonException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (DuplicateKeyException e) {
            log.error("DuplicateKeyException错误,类:[{}],异常:",this.getClass().getName(), e);
            response.setCode(ResponseCodeConstant.DB_EXCEPTION.getResponseCode());
            response.setContent("存在不可重复数据:" + e.getCause().getMessage());
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
    protected abstract Response callInner(CommonRequest<Request> request) throws Exception;
}
