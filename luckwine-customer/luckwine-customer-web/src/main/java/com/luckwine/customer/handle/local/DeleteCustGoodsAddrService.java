package com.luckwine.customer.handle.local;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustGoodsAddrMapper;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Log
public class DeleteCustGoodsAddrService extends SingleTemplate<CustGoodsAddr,Boolean> {

    @Resource
    private CustGoodsAddrMapper custGoodsAddrMapper;

    @Override
    protected Boolean callInner(CommonRequest<CustGoodsAddr> request) throws Exception {
        QueryWrapper<CustGoodsAddr> wrapper = new QueryWrapper<CustGoodsAddr>();
        wrapper.eq("goods_addr_id",request.getRequest().getGoodsAddrId());

        return custGoodsAddrMapper.delete(wrapper)>0;

    }
}
