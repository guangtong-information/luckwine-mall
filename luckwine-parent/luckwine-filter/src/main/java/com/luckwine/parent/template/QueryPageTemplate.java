package com.luckwine.parent.template;

import com.alibaba.dubbo.rpc.RpcException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.util.ExceptionUtils;
import com.luckwine.parent.util.RequestLogUtils;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 公用抽象模板
 *
 * 调用Dao层抽象模板，支持分页
 *
 * author:Winlone
 *
 * @param <Request>    最外层请求对象
 * @param <DBPO>  数据库
 */
@Slf4j
public abstract class QueryPageTemplate<Request, DBPO> {

    /**
     * 外层call
     *
     * @param request
     * @return
     */
    public CommonQueryPageResponse call(CommonQueryPageRequest<Request> request) {
        CommonQueryPageResponse<List<DBPO>> response = new CommonQueryPageResponse<>();
        try {
            request.setTraceId(RequestLogUtils.getCurTraceId(request));
            request.setOperLevel(RequestLogUtils.getCurOperLevel(request));
            PageHelper.startPage(request.getPageNo(), request.getPageSize());
            List<DBPO> list = callInner(request);
            PageInfo pageInfo = new PageInfo(list);
            response.setResponse(pageInfo.getList());
            response.setTotalCount(pageInfo.getTotal());
            response.setTotalPage(pageInfo.getPages());
            response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
        }   catch (ConstraintViolationException e) {
            ConstraintViolationException cvException = (ConstraintViolationException)e;
            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
            response.setContent(ExceptionUtils.getMsg(cvException));
        }  catch (ParamErrorException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        }catch (CommonException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        }  catch (RpcException e) {
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
    protected abstract List<DBPO> callInner(CommonQueryPageRequest<Request> request) throws Exception;

}
