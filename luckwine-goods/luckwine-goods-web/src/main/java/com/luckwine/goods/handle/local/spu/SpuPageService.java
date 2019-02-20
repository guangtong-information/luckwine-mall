package com.luckwine.goods.handle.local.spu;

import com.luckwine.goods.dao.SpuMapper;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.base.Spu;
import com.luckwine.goods.model.request.spu.SpuPageRequest;
import com.luckwine.goods.model.response.SpuInfo;
import com.luckwine.goods.utils.GoodsUtils;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageAggregationTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpuPageService extends QueryPageAggregationTemplate<SpuPageRequest, List<SpuInfo>,Spu> {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private GoodsUtils goodsUtils;

    @Override
    protected List<Spu> callInner(CommonQueryPageRequest<SpuPageRequest> request) throws Exception {
        Example example = new Example(Spu.class);
        if (StringUtils.isNotBlank(request.getRequest().getGoodsName())) {
            example.createCriteria().andLike("goodsName", "%" + request.getRequest().getGoodsName() +"%");
        }
        return spuMapper.selectByExample(example);
    }

    @Override
    protected List<SpuInfo> transformationResponse(List<Spu> response, CommonQueryPageRequest<SpuPageRequest> request) throws Exception {
        List<SpuInfo> list = new ArrayList<>();

        Set<Long> brandIds =  response.stream().map(Spu::getBrandId).collect(Collectors.toSet());
        Set<Long> categoryIds =  response.stream().map(Spu::getCategoryId).collect(Collectors.toSet());

        List<Brand> brands = goodsUtils.getBrands(brandIds);
        List<Category> categorys = goodsUtils.getCategorys(categoryIds);

        Map<Long, String> brandMap = brands.stream().collect(Collectors.toMap(Brand::getId,Brand::getBrandName ));
        Map<Long, String> categoryMap = categorys.stream().collect(Collectors.toMap(Category::getId,Category::getCategoryName ));

        for (Spu spu : response) {
            SpuInfo spuInfo = new SpuInfo();
            spuInfo.setSpu(spu);
            spuInfo.setBrandName(brandMap.get(spu.getBrandId()));
            spuInfo.setCategoryName(categoryMap.get(spu.getCategoryId()));
            list.add(spuInfo);
        }
        return list;
    }
}
