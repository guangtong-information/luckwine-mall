package com.luckwine.goods.handle.local.spu;

import com.luckwine.goods.dao.SkuMapper;
import com.luckwine.goods.dao.SpuMapper;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.base.Sku;
import com.luckwine.goods.model.base.Spu;
import com.luckwine.goods.model.request.spu.SpuDetailRequest;
import com.luckwine.goods.model.response.SpuDetail;
import com.luckwine.goods.utils.GoodsUtils;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SpuDetailService extends SingleTemplate<SpuDetailRequest, SpuDetail> {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private GoodsUtils goodsUtils;

    @Override
    protected SpuDetail callInner(CommonRequest<SpuDetailRequest> request) throws Exception {

        Spu spu = spuMapper.selectByPrimaryKey(request.getRequest().getId());
        if (spu == null) {
            throw new ParamErrorException("id错误");
        }

        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setBrandName(goodsUtils.getBrandName(spu.getBrandId()));
        spuDetail.setCategoryName(goodsUtils.getCategoryName(spu.getCategoryId()));
        spuDetail.setSpu(spu);

        Example example = new Example(Sku.class);
        example.createCriteria().andEqualTo("spuId",spu.getId());
        List<Sku> skus = skuMapper.selectByExample(example);
        spuDetail.setSkus(skus);

        return spuDetail;
    }
}
