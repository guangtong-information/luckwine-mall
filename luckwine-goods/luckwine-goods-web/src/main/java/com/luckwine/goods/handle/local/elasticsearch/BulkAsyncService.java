package com.luckwine.goods.handle.local.elasticsearch;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BulkAsyncService extends SingleTemplate<BulkRequest, Boolean> {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    protected Boolean callInner(CommonRequest<BulkRequest> request) throws Exception {
        restHighLevelClient.bulkAsync(request.getRequest(), RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                if (bulkItemResponses.hasFailures()) {
                    for (BulkItemResponse bulkItemResponse : bulkItemResponses) {
                        if (bulkItemResponse.isFailed()) {
                            log.error("同步出现异常:", bulkItemResponse.getFailure().getCause());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Exception e) {
                log.error("Bulk异常", e);
            }
        });
        return true;
    }
}
