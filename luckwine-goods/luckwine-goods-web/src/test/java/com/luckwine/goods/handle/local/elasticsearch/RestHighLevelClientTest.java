package com.luckwine.goods.handle.local.elasticsearch;


import com.luckwine.goods.GoodsApplication;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class RestHighLevelClientTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Test
    public void test() throws IOException {

        int pageSize = 1;
        int pageNo = 10;
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.from((pageNo - 1) * pageSize);

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        TermsAggregationBuilder categoryAggregation = AggregationBuilders.terms("categoryIds")
                .field("categoryId").size(2);


        TermsAggregationBuilder brandAggregation = AggregationBuilders.terms("brandIds")
                .field("brandId").size(2000);


        searchSourceBuilder.aggregation(brandAggregation);
        searchSourceBuilder.aggregation(categoryAggregation);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();

        for (SearchHit hit : hits) {
            log.info("{}", hit.getSourceAsString());
        }

        Aggregations aggregations = searchResponse.getAggregations();
        for (Aggregation agg : aggregations.asList()) {
            ParsedLongTerms terms = (ParsedLongTerms) agg;
            for (Terms.Bucket bucket :terms.getBuckets()){
             log.info("{}:{}",  bucket.getKeyAsString(), bucket.getDocCount());
            }
        }

        log.info("ok");
    }
}
