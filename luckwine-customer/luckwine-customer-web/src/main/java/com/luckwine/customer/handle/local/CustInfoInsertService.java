package com.luckwine.customer.handle.local;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustInfoMapper;
import com.luckwine.customer.model.base.CustCollect;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.enums.ExceptionCode;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Log
@Transactional
public class CustInfoInsertService extends SingleTemplate<CustInfo,Boolean> {

    @Resource
    private CustInfoMapper custInfoMapper;

    @Override
    protected Boolean callInner(CommonRequest<CustInfo> request) throws Exception {

        CustInfo custInfo = request.getRequest();
        if(StringUtils.isNotEmpty(custInfo.getLoginAccount())) {
            QueryWrapper<CustInfo> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("login_account",custInfo.getLoginAccount());
           if(custInfoMapper.selectCount(queryWrapper) > 0){
               throw new CommonException(ExceptionCode.has_account.getCode(), ExceptionCode.has_account.getMessage());
           }
        } else {
            throw new CommonException(ExceptionCode.not_login_account.getCode(), ExceptionCode.not_login_account.getMessage());
        }

        int row = 0;
        Boolean result = false;
        row = custInfoMapper.insert(custInfo);
        if (row>0){
            result = true;
        }

        return result;
    }
}
