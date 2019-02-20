package com.luckwine.goods.handle.local.brand;

import com.luckwine.goods.dao.BrandMapper;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.request.brand.BrandSaveRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BrandSaveService extends SingleTemplate<BrandSaveRequest, Boolean> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    protected Boolean callInner(CommonRequest<BrandSaveRequest> request) throws Exception {
       Brand brand = new Brand();
       brand.setBrandName(request.getRequest().getBrandName());
       brand.setCreateTime(new Date());
        int count = brandMapper.insertSelective(brand);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
