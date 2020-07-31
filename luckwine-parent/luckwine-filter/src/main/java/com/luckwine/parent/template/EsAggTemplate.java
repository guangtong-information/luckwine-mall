package com.luckwine.parent.template;


import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.AggregationInfo;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;
import com.luckwine.parent.util.RequestLogUtils;
import com.luckwine.parent.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * es聚合查询
 */
@Slf4j
public abstract class EsAggTemplate {

    public EalsticsearchPageResponse<SearchHits> call(CommonQueryPageRequest<SearchRequest> request) {
        EalsticsearchPageResponse<SearchHits> response = new EalsticsearchPageResponse();
        try {
            if (!StringUtils.isEmpty(SpringContextUtil.getApplicationName())) {
                request.setAppName(SpringContextUtil.getApplicationName());
            }
            request.setTraceId(RequestLogUtils.getCurTraceId(request));
            request.setOperLevel(RequestLogUtils.getCurOperLevel(request));
            SearchResponse searchResponse = callInner(request);
            RestStatus restStatus = searchResponse.status();
            if (RestStatus.OK.getStatus() != restStatus.getStatus()) {
                throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "ES:" + RestStatus.fromCode(restStatus.getStatus()).name());
            }
            Aggregations aggregations = searchResponse.getAggregations();


            Map<String, List<AggregationInfo>> aggregationInfoMap = new HashMap<>();

            if (aggregations != null) {
                for (Aggregation agg : aggregations.asList()) {
                    List<AggregationInfo> aggregationInfoList = new ArrayList<>();
                    if (agg instanceof ParsedLongTerms) {
                        ParsedLongTerms terms = (ParsedLongTerms) agg;
                        for (Terms.Bucket bucket : terms.getBuckets()) {
                            AggregationInfo aggregationInfo = new AggregationInfo();
                            aggregationInfo.setKey(bucket.getKeyAsNumber().longValue());
                            aggregationInfo.setCount(bucket.getDocCount());
                            aggregationInfoList.add(aggregationInfo);
                        }
                        aggregationInfoMap.put(terms.getName(), aggregationInfoList);
                    }
                }
            }
            response.setAggregationInfoMap(aggregationInfoMap);
            response.setResponse(searchResponse.getHits());
            Long pageCount = (searchResponse.getHits().getTotalHits() + request.getPageSize() - 1) / request.getPageSize();
            response.setTotalPage(pageCount.intValue());
            response.setTotalCount(searchResponse.getHits().getTotalHits());
            response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
            return response;
        } catch (CommonException e) {
            return EalsticsearchPageResponse.empty(e.getCode(), e.getMessage());
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                return EalsticsearchPageResponse.empty();
            } else {
                log.error("ElasticsearchException,类:[{}],异常:", this.getClass().getName(), e);
                return EalsticsearchPageResponse.empty(ResponseCodeConstant.SYS_EXCEPTION);
            }
        } catch (Exception e) {
            log.error("Exception错误,类:[{}],异常:", this.getClass().getName(), e);
            return EalsticsearchPageResponse.empty(ResponseCodeConstant.SYS_EXCEPTION);
        }
    }

    protected abstract SearchResponse callInner(CommonQueryPageRequest<SearchRequest> request) throws Exception;

}
