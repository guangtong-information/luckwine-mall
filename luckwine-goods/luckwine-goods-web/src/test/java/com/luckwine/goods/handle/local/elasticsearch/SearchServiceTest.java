package com.luckwine.goods.handle.local.elasticsearch;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void callInner() throws Exception {
        CommonQueryPageRequest<SearchRequest> request = new CommonQueryPageRequest<>();
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(2);
        searchRequest.source(searchSourceBuilder);
        request.setPageNo(1);
        request.setPageSize(2);
        request.setRequest(searchRequest);
        CommonQueryPageResponse<SearchHit[]> response = searchService.call(request);
        log.info("{}", response);
    }
}