package com.luckwine.customer.handle.local;

import com.luckwine.customer.dao.CustGoodsAddrMapper;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Log
public class InsertCustGoodsAddrService extends SingleTemplate<CustGoodsAddr,Boolean> {

    @Resource
    private CustGoodsAddrMapper custGoodsAddrMapper;

    @Override
    protected Boolean callInner(CommonRequest<CustGoodsAddr> request) throws Exception {
        return custGoodsAddrMapper.insert(request.getRequest())>0;

    }
}
