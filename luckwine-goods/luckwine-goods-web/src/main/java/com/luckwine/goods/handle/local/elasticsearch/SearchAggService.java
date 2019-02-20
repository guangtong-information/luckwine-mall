package com.luckwine.goods.handle.local.elasticsearch;

import com.luckwine.goods.utils.GoodsUtils;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.EsAggTemplate;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchAggService extends EsAggTemplate {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    protected SearchResponse callInner(CommonQueryPageRequest<SearchRequest> request) throws Exception {
        return  restHighLevelClient.search(request.getRequest(), RequestOptions.DEFAULT);
    }


}
