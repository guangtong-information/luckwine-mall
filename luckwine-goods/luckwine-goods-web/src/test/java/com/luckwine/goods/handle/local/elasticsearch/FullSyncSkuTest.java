package com.luckwine.goods.handle.local.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.dao.AggregationMapper;
import com.luckwine.goods.model.base.vo.SkuDetail;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * 全量同步sku到 es
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class FullSyncSkuTest {

    @Autowired
    private AggregationMapper aggregationMapper;


    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @org.junit.Test
    public void test() throws InterruptedException {

        List<SkuDetail> list = aggregationMapper.findAll();

        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                int numberOfActions = request.numberOfActions();
                log.info("Executing bulk [{}] with {} requests",
                        executionId, numberOfActions);
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                if (response.hasFailures()) {
                    log.error("Bulk [{}] executed with failures", executionId);
                } else {
                    log.info("Bulk [{}] completed in {} milliseconds",
                            executionId, response.getTook().getMillis());
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                log.error("Failed to execute bulk", failure);
            }
        };

        BiConsumer<BulkRequest, ActionListener<BulkResponse>> bulkConsumer =
                (req, bulkListener) -> restHighLevelClient.bulkAsync(req, RequestOptions.DEFAULT, bulkListener);
        BulkProcessor bulkProcessor = BulkProcessor.builder(bulkConsumer, listener).build();
        BulkProcessor.Builder builder = BulkProcessor.builder(bulkConsumer, listener);
        builder.setBulkActions(500);
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        builder.setConcurrentRequests(0);
        builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
        builder.setBackoffPolicy(BackoffPolicy
                .constantBackoff(TimeValue.timeValueSeconds(1L), 3));

        for (SkuDetail skuDetail : list) {
            IndexRequest indexRequest = new IndexRequest("goods", "sku", skuDetail.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(skuDetail), XContentType.JSON);
            bulkProcessor.add(indexRequest);
        }
        boolean terminated = bulkProcessor.awaitClose(300L, TimeUnit.SECONDS);
        log.info("ok: {}", terminated );
        bulkProcessor.close();
    }

}
