package com.luckwine.goods.handle.local.sku;

import com.alibaba.fastjson.JSON;
import com.luckwine.goods.handle.local.elasticsearch.SearchAggService;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.enums.SearchSort;
import com.luckwine.goods.model.request.sku.SearchSkuRequest;
import com.luckwine.goods.utils.GoodsUtils;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.EalsticsearchPageResponse;
import com.luckwine.parent.template.TransEsAggPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkuSearchAggService extends TransEsAggPageTemplate<SearchSkuRequest, List<SkuDetail>, SearchRequest, EalsticsearchPageResponse<SearchHits>, SearchHits> {

    @Autowired
    private SearchAggService searchAggService;

    @Autowired
    private GoodsUtils goodsUtils;

    private static final String AGG_TERMS_BRAND_KEY = "BRAND";

    private static final String AGG_TERMS_CATEGORY_KEY = "CATEGORY";

    private static final String QRY_SKU_NAME_NAME = "skuName.ik";
    // private static final String QRY_SKU_NAME_NAME = "skuName";



    @Override
    protected EalsticsearchPageResponse<SearchHits> call(CommonQueryPageRequest<SearchRequest> req) {
        return searchAggService.call(req);
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
            //boolBuilder.must(QueryBuilders.multiMatchQuery(request.getText(), "goodsName", "skuName", "propsStr", "skuPropsStr"));
          //  boolBuilder.must(QueryBuilders.multiMatchQuery(request.getText(),"goodsName",  QRY_SKU_NAME_NAME));
            boolBuilder.must(QueryBuilders.matchQuery(QRY_SKU_NAME_NAME, request.getText()));


            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field(new HighlightBuilder.Field(QRY_SKU_NAME_NAME));
       //     highlightBuilder.field(new HighlightBuilder.Field("goodsName"));
            highlightBuilder.preTags("<span style=\"color: red\">");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        if (request.getBrandId() != null) {
            boolBuilder.must(QueryBuilders.matchQuery("brandId", request.getBrandId()));
        }

        if (request.getCategoryId() != null) {
            boolBuilder.must(QueryBuilders.matchQuery("categoryId", request.getCategoryId()));
        }

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");

        if (request.getPriceGte() != null) {
            rangeQueryBuilder.gte(request.getPriceGte());
        }

        if (request.getPriceLte() != null) {
            rangeQueryBuilder.lte(request.getPriceLte());
        }

        if (request.getPriceGte() != null || request.getPriceLte() != null) {
            boolBuilder.filter(rangeQueryBuilder);
        }

        if (boolBuilder.hasClauses()) {
            searchSourceBuilder.query(boolBuilder);
        } else {
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        }


        TermsAggregationBuilder brandAggregation = AggregationBuilders.terms(AGG_TERMS_BRAND_KEY)
                .field("brandId").size(20);


        TermsAggregationBuilder categoryAggregation = AggregationBuilders.terms(AGG_TERMS_CATEGORY_KEY)
                .field("categoryId").size(20);

        searchSourceBuilder.aggregation(categoryAggregation);
        searchSourceBuilder.aggregation(brandAggregation);


        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    @Override
    protected List<SkuDetail> transformerResponse(SearchHits searchHits) {
        List<SkuDetail> list = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            SkuDetail skuDetail = JSON.parseObject(searchHit.getSourceAsString(), SkuDetail.class);
  //          skuDetail.setGoodsName(getHighlightStr(searchHit, "goodsName", skuDetail.getGoodsName()));
            skuDetail.setSkuName(getHighlightStr(searchHit, QRY_SKU_NAME_NAME, skuDetail.getSkuName()));
            list.add(skuDetail);
        }
        return list;
    }

    /**
     *
     * @param searchHit 结果
     * @param field 高亮的属性
     * @param original 原来的值
     * @return
     */
    private String getHighlightStr(SearchHit searchHit, String field, String original) {
        Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
        if (!highlightFields.isEmpty()) {
            HighlightField skuHighlight = highlightFields.get(field);
            if (skuHighlight != null && skuHighlight.fragments() != null && skuHighlight.fragments().length > 0) {
                Text[] skuFragments = skuHighlight.fragments();
                String skuFragmentString = skuFragments[0].string();
                return skuFragmentString;
            }
        }
        return original;
    }

    @Override
    protected Map<Long, String> getNameMap(String field, Set<Long> keys) {
        if (AGG_TERMS_BRAND_KEY.equals(field)) {
            List<Brand> brands = goodsUtils.getBrands(keys);
            return brands.stream().collect(Collectors.toMap(Brand::getId, Brand::getBrandName));
        } else if (AGG_TERMS_CATEGORY_KEY.equals(field)) {
            List<Category> categories = goodsUtils.getCategorys(keys);
            return categories.stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));
        }
        return null;
    }
}
