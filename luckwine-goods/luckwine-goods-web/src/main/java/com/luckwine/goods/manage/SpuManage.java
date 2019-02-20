package com.luckwine.goods.manage;

import com.luckwine.goods.dao.BrandMapper;
import com.luckwine.goods.dao.CategoryMapper;
import com.luckwine.goods.dao.SkuMapper;
import com.luckwine.goods.dao.SpuMapper;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.base.Sku;
import com.luckwine.goods.model.base.Spu;
import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.goods.model.request.spu.SkuVO;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.goods.model.response.Props;
import com.luckwine.goods.utils.GoodsIdUtils;
import com.luckwine.goods.utils.GoodsUtils;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SpuManage {

    @Autowired
    private GoodsUtils goodsUtils;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private GoodsIdUtils goodsIdUtils;


    /* 开启事务， SingleTemplate 事务不生效   */
    @Transactional
    public Boolean save(CommonRequest<SpuSaveRequest> request) throws Exception {
        Spu spu = new Spu();
        spu.setId(goodsIdUtils.generateId());
        spu.setCreateTime(new Date());
        spu.setGoodsName(request.getRequest().getGoodsName());
        spu.setStatus(request.getRequest().getSpuStatus().name());
        spu.setTitle(request.getRequest().getTitle());
        spu.setDetail(request.getRequest().getDetail());
        spu.setPicPath(request.getRequest().getPicPath());
        spu.setProps(request.getRequest().getProps());
        spu.setCategoryId(request.getRequest().getCategoryId());
        spu.setBrandId(request.getRequest().getBrandId());
        Category category = categoryMapper.selectByPrimaryKey(request.getRequest().getCategoryId());
        if (category == null) {
            throw new ParamErrorException("分类id错误");
        }

        Brand brand = brandMapper.selectByPrimaryKey(request.getRequest().getBrandId());
        if (brand == null) {
            throw new ParamErrorException("品牌id错误");
        }

        List<Sku> skus = new ArrayList<>();
        for (SkuVO skuVO : request.getRequest().getSkus()) {
            List<Props> propsList = goodsUtils.getPropsList(skuVO.getProps());
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            sku.setPropsStr(goodsUtils.getPropsListStr(propsList));
            sku.setProps(skuVO.getProps());
            sku.setSkuName(skuVO.getSkuName());
            sku.setQuantity(skuVO.getQuantity());
            sku.setPrice(skuVO.getPrice());
            sku.setCreateTime(new Date());
            sku.setStatus(SkuStatus.IN_STOCK.name());
            skus.add(sku);
        }
        skuMapper.insertList(skus);

        if (StringUtils.isNotBlank(request.getRequest().getProps())) {
             List<Props> propsList = goodsUtils.getPropsList(request.getRequest().getProps());
             spu.setPropsStr(goodsUtils.getPropsListStr(propsList));
        }

        int count  = spuMapper.insertSelective(spu);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
