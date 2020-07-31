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
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;

/*
*  es分页查询模板
*
* */
@Slf4j
public abstract class EsPageTemplate  {

    /**
     * 外层call
     *
     * @param request
     * @return
     */
    public CommonQueryPageResponse<SearchHit[]> call(CommonQueryPageRequest<SearchRequest> request) {
        CommonQueryPageResponse<SearchHit[]> response = new CommonQueryPageResponse<>();
        try {
            if(!StringUtils.isEmpty(SpringContextUtil.getApplicationName())) {
                request.setAppName(SpringContextUtil.getApplicationName());
            }
            request.setTraceId(RequestLogUtils.getCurTraceId(request));
            request.setOperLevel(RequestLogUtils.getCurOperLevel(request));
            //请求remote
            SearchHits result = callInner(request);
            Long pageCount = (result.getTotalHits() + request.getPageSize() -1) / request.getPageSize();
            response.setTotalPage(pageCount.intValue());
            response.setTotalCount(result.getTotalHits());
            response.setResponse(result.getHits());
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


    protected abstract SearchHits callInner(CommonQueryPageRequest<SearchRequest> request) throws Exception;
}
