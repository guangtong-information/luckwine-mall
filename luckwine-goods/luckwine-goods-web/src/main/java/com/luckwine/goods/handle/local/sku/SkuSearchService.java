package com.luckwine.goods.handle.local.sku;

import com.alibaba.fastjson.JSON;
import com.luckwine.goods.handle.local.elasticsearch.SearchService;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.enums.SearchSort;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.template.TransPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuSearchService extends TransPageTemplate<SearchSkuRequest, List<SkuDetail>, SearchRequest, CommonQueryPageResponse<SearchHit[]>, SearchHit[]> {

    @Autowired
    private SearchService searchService;

    @Override
    protected CommonQueryPageResponse<SearchHit[]> call(CommonQueryPageRequest<SearchRequest> req) {
        return searchService.call(req);
    }

    @Override
    protected SearchRequest transformRequest(CommonQueryPageRequest<SearchSkuRequest> req) {
        SearchSkuRequest request = req.getRequest();
        SearchRequest searchRequest = new SearchRequest("goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(req.getPageSize());
        searchSourceBuilder.from((req.getPageNo() - 1) * req.getPageSize());

        if (SearchSort.PRICE_ASC == req.getRequest().getSearchSort()) {
            searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));
        } else if (SearchSort.PRICE_DESC == req.getRequest().getSearchSort()) {
            searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.DESC));
        }

        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();

        if (StringUtils.isNotBlank(request.getText())) {
            boolBuilder.should().add(QueryBuilders.boolQuery().must(QueryBuilders.multiMatchQuery(request.getText(),"goodsName","skuName","propsStr","skuPropsStr")));
        }

        if (request.getBrandId() != null) {
            boolBuilder.should().add(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("brandId", request.getBrandId())));
        }

        if (request.getCategoryId() != null) {
            boolBuilder.should().add(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("categoryId", request.getCategoryId())));
        }

        if (request.getPriceGte() != null) {
            boolBuilder.should().add(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("price").gte(request.getPriceGte())));
        }

        if (request.getPriceLte() != null) {
            boolBuilder.should().add(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("price").gte(request.getPriceLte())));
        }

        if (boolBuilder.should().size() > 0) {
            searchSourceBuilder.query(boolBuilder);
        } else {
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        }

        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    @Override
    protected List<SkuDetail> transformerResponse(SearchHit[] searchHits) {
        List<SkuDetail> list = new ArrayList<>();
        for(SearchHit searchHit: searchHits) {
            SkuDetail skuDetail =  JSON.parseObject(searchHit.getSourceAsString(),SkuDetail.class);
            list.add(skuDetail);
        }
        return list;
    }
}
