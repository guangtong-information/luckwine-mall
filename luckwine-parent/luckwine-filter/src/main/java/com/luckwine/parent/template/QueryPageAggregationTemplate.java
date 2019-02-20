package com.luckwine.parent.template;

import com.alibaba.dubbo.rpc.RpcException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.util.ExceptionUtils;
import com.luckwine.parent.util.RequestLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class QueryPageAggregationTemplate  <Request, Response, DBPO> {

    public CommonQueryPageResponse call(CommonQueryPageRequest<Request> request) {
        CommonQueryPageResponse<Response> response = new CommonQueryPageResponse<>();
        try {
            request.setTraceId(RequestLogUtils.getCurTraceId(request));
            request.setOperLevel(RequestLogUtils.getCurOperLevel(request));
            PageHelper.startPage(request.getPageNo(), request.getPageSize());
            List<DBPO> list = callInner(request);
            PageInfo pageInfo = new PageInfo(list);
            if (CollectionUtils.isEmpty(list)) {
                response.setResponse((Response)new ArrayList<>());
            } else {
                response.setResponse(transformationResponse(list, request));
            }
            response.setTotalCount(pageInfo.getTotal());
            response.setTotalPage(pageInfo.getPages());
            response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
        }   catch (ConstraintViolationException e) {
            ConstraintViolationException cvException = (ConstraintViolationException)e;
            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
            response.setContent(ExceptionUtils.getMsg(cvException));
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


    protected abstract List<DBPO> callInner(CommonQueryPageRequest<Request> request) throws Exception;

    protected abstract Response transformationResponse(List<DBPO> response,CommonQueryPageRequest<Request> request) throws Exception;




}
