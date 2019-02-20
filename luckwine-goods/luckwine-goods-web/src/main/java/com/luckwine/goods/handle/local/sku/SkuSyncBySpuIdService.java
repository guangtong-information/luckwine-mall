package com.luckwine.goods.handle.local.sku;

import com.alibaba.fastjson.JSON;
import com.luckwine.goods.dao.AggregationMapper;
import com.luckwine.goods.handle.local.elasticsearch.BulkAsyncService;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SyncSkuBySpuIdRequest;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.TransTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuSyncBySpuIdService extends TransTemplate<SyncSkuBySpuIdRequest,Boolean, BulkRequest, CommonResponse<Boolean>, Boolean> {

    @Autowired
    private AggregationMapper aggregationMapper;

    @Autowired
    private BulkAsyncService bulkAsyncService;

    @Override
    protected CommonResponse<Boolean> call(CommonRequest<BulkRequest> req) {
        return bulkAsyncService.call(req);
    }

    @Override
    protected BulkRequest transformRequest(SyncSkuBySpuIdRequest request) {
        List<SkuDetail> list =  aggregationMapper.findBySpuId(request.getSpuId());
        if (CollectionUtils.isEmpty(list)) {
            throw new ParamErrorException("spuId错误");
        }

        BulkRequest bulkRequest = new BulkRequest();
        for (SkuDetail skuDetail : list) {
            IndexRequest indexRequest = new IndexRequest("goods", "sku", skuDetail.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(skuDetail), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        return bulkRequest;
    }

    @Override
    protected Boolean transformerResponse(Boolean aBoolean, CommonRequest<SyncSkuBySpuIdRequest> req) {
        return aBoolean;
    }


}
