package com.luckwine.customer.handle.local;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustInfoMapper;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.request.CustomerServiceReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import com.luckwine.parent.tools.date.DateBetween;
import lombok.extern.java.Log;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Log
@Transactional
public class CustInfoPageService extends QueryPageTemplate<CustomerServiceReq,  CustInfo> {

    @Resource
    private CustInfoMapper custInfoMapper;


    @Override
    protected List<CustInfo> callInner(CommonQueryPageRequest<CustomerServiceReq> request) throws Exception {
        CustomerServiceReq customerServiceReq = request.getRequest();
        CustInfo custInfo = new CustInfo();
        BeanUtils.copyProperties(customerServiceReq, custInfo);

        QueryWrapper<CustInfo> entityWrapper = new QueryWrapper<>();
        entityWrapper.like(StringUtils.isNotEmpty(custInfo.getNickname()),"nickname", custInfo.getNickname())
                .like(StringUtils.isNotEmpty(custInfo.getLoginAccount()),"login_account", custInfo.getLoginAccount())
                .like(StringUtils.isNotEmpty(custInfo.getIdentity()),"identity", custInfo.getIdentity());

        custInfo.setNickname(null);
        custInfo.setLoginAccount(null);
        custInfo.setIdentity(null);

        if(StringUtils.isNotEmpty(customerServiceReq.getCreateStartDate()) &&  StringUtils.isNotEmpty(customerServiceReq.getCreateEndDate()) ){
            Date startDate = new DateBetween().startDate(customerServiceReq.getCreateStartDate());
            Date endDate =  new DateBetween().endDate(customerServiceReq.getCreateEndDate());
            entityWrapper.between("create_time", startDate,  endDate);
        }
        entityWrapper.setEntity(custInfo);
        return custInfoMapper.selectList(entityWrapper);
    }
}