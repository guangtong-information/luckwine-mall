package com.luckwine.goods.handle.local.elasticsearch;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.EsPageTemplate;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService extends EsPageTemplate {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    protected SearchHits callInner(CommonQueryPageRequest<SearchRequest> request) throws Exception {
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(request.getRequest(), RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            //如果查找不到记录
            if (e.status() == RestStatus.NOT_FOUND) {
                return SearchHits.empty();
            }   else {
             throw e;
            }
        }
        RestStatus restStatus  = searchResponse.status();
        if (RestStatus.OK.getStatus() != restStatus.getStatus()) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(),"ES:" + RestStatus.fromCode(restStatus.getStatus()).name());
        }
        return searchResponse.getHits();
    }
}
