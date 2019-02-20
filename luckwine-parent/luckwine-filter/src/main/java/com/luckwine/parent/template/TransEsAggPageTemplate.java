package com.luckwine.parent.template;

import com.alibaba.dubbo.rpc.RpcException;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.BaseRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.AggregationInfo;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;
import com.luckwine.parent.util.ExceptionUtils;
import com.luckwine.parent.util.RequestLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * author:zhouyanjie 2018.10.25
 *
 * @param <REQ>         原来的请求
 * @param <Result>      原来的响应
 * @param <TransReq>    转换后的请求
 * @param <TransResult> 转换后的响应
 */
@Slf4j
public abstract class TransEsAggPageTemplate<REQ extends BaseRequest, Result, TransReq, TransResult extends EalsticsearchPageResponse<RESP>, RESP> {

    public EalsticsearchPageResponse<Result> callInner(CommonQueryPageRequest<REQ> req) {
        EalsticsearchPageResponse<Result> response = new EalsticsearchPageResponse<>();
        try {
            TransReq transReq = transformRequest(req);
            CommonQueryPageRequest<TransReq> commonRequest = new CommonQueryPageRequest<>();
            commonRequest.setChannelCode(req.getChannelCode());
            commonRequest.setTraceId(RequestLogUtils.getCurTraceId(req));
            commonRequest.setOperLevel(RequestLogUtils.getCurOperLevel(req));
            commonRequest.setAppName(req.getAppName());
            commonRequest.setPageNo(req.getPageNo());
            commonRequest.setPageSize(req.getPageSize());
            commonRequest.setRequest(transReq);
            TransResult transResp = call(commonRequest);
            response.setTotalPage(transResp.getTotalPage());
            response.setTotalCount(transResp.getTotalCount());
            response.setCode(transResp.getCode());
            response.setContent(transResp.getContent());
            if (!CollectionUtils.isEmpty(transResp.getAggregationInfoMap())) {
                response.setAggregationInfoMap(transformerAggregationInfo(transResp.getAggregationInfoMap()));
            }
            if (transResp.getResponse() != null) {
                response.setResponse(transformerResponse(transResp.getResponse()));
            }
        } catch (ConstraintViolationException e) {
            ConstraintViolationException cvException = (ConstraintViolationException) e;
            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
            response.setContent(ExceptionUtils.getMsg(cvException));
        } catch (ParamErrorException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (CommonException e) {
            response.setCode(e.getCode());
            response.setContent(e.getMessage());
        } catch (DuplicateKeyException e) {
            log.error("DuplicateKeyException错误,类:[{}],异常:", this.getClass().getName(), e);
            response.setCode(ResponseCodeConstant.DB_EXCEPTION.getResponseCode());
            response.setContent("存在不可重复数据:" + e.getCause().getMessage());
        } catch (RpcException e) {
            log.error("RpcException超时,类:[{}],请求:[{}],错误类型:[{}],异常:", this.getClass().getName(), req.toString(), e.getCode(), e);
            response.setCode(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseCode());
            response.setContent(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseDesc());
        } catch (Exception e) {
            log.error("Exception错误,类:[{}],异常:", this.getClass().getName(), e);
            response.setCode(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
            response.setContent(ResponseCodeConstant.SYS_EXCEPTION.getResponseDesc());
        }
        return response;
    }


    /* 转换后的调用， 跟响应 */
    protected abstract TransResult call(CommonQueryPageRequest<TransReq> req);


    /* 将请求对象进行转换 */
    protected abstract TransReq transformRequest(CommonQueryPageRequest<REQ> req);


    /* 将结果对象进行转换 */
    protected abstract Result transformerResponse(RESP resp);

    /**
     * 根据key 获取名称的 map
     */
    protected abstract Map<Long, String> getNameMap(String field, Set<Long> keys);

    private Map<String, List<AggregationInfo>> transformerAggregationInfo(Map<String, List<AggregationInfo>> map) {
        for (String key : map.keySet()) {
            List<AggregationInfo> aggregationInfoList = map.get(key);
            //根据  批量key 批量获取 map
            Set<Long> keys = aggregationInfoList.stream().map(AggregationInfo::getKey).collect(Collectors.toSet());
            if (!CollectionUtils.isEmpty(keys)) {
                Map<Long, String> nameMap = getNameMap(key, keys);
                if (!CollectionUtils.isEmpty(nameMap)) {
                    for (AggregationInfo aggregationInfo : aggregationInfoList) {
                        aggregationInfo.setName(nameMap.get(aggregationInfo.getKey()));
                    }
                }
            }
        }
        return map;
    }
}
