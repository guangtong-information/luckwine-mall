package com.luckwine.goods.handle.local.brand;

import com.luckwine.goods.dao.BrandMapper;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.request.brand.BrandPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandPageService extends QueryPageTemplate<BrandPageRequest,  Brand> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    protected List<Brand> callInner(CommonQueryPageRequest<BrandPageRequest> request) throws Exception {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(request.getRequest().getBrandName())) {
            criteria.andLike("brandName", "%" + request.getRequest().getBrandName() + "%");
        }
        return brandMapper.selectByExample(example);
    }

}
