package com.luckwine.goods.handle.local.sku;

import com.luckwine.goods.dao.AggregationMapper;
import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuDetailGetByIdsService extends SingleTemplate<SkuDetailGetByIdsRequest, List<SkuDetail>> {

    @Autowired
    private AggregationMapper aggregationMapper;

    @Override
    protected List<SkuDetail> callInner(CommonRequest<SkuDetailGetByIdsRequest> request) throws Exception {
        return aggregationMapper.findBySkuIds(request.getRequest().getSkuIds());
    }
}
