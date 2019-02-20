package com.luckwine.customer.handle.local;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustCollectMapper;
import com.luckwine.customer.model.base.CustCollect;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Log
@Transactional
public class CustCollectPageService extends QueryPageTemplate<CustCollect,  CustCollect> {
    @Resource
    private CustCollectMapper custCollectMapper;

    @Override
    protected List<CustCollect> callInner(CommonQueryPageRequest<CustCollect> request) throws Exception {
        CustCollect custCollect=request.getRequest();
        QueryWrapper<CustCollect> queryWrapper = new QueryWrapper<CustCollect>();
        queryWrapper.eq("login_account",custCollect.getLoginAccount());
        return custCollectMapper.selectList(queryWrapper);
    }
}
