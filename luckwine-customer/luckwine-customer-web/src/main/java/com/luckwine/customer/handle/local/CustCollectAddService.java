package com.luckwine.customer.handle.local;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustCollectMapper;
import com.luckwine.customer.model.base.CustCollect;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
@Log
@Transactional
public class CustCollectAddService extends SingleTemplate<CustCollect, Boolean> {

    @Resource
    private CustCollectMapper custCollectMapper;


    @Override
    protected Boolean callInner(CommonRequest<CustCollect> request) throws Exception {
        CustCollect custCollect=request.getRequest();
        QueryWrapper<CustCollect> queryWrapper=new QueryWrapper<CustCollect>();
        queryWrapper
                .eq("login_account",custCollect.getLoginAccount())
                .eq("goods_id",custCollect.getGoodsId());
        CustCollect oldcustCollect = custCollectMapper.selectOne(queryWrapper);
        if (oldcustCollect!=null){
            return false;
        }
        Integer i=custCollectMapper.insert(custCollect);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
}
