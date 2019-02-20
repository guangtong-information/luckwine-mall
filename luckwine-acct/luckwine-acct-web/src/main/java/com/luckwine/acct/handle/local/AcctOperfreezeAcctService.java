package com.luckwine.acct.handle.local;

import com.luckwine.acct.dao.AcctInfoMapper;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctFreezeBatchRequest;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;

import javax.annotation.Resource;
import java.util.Date;

public class AcctOperfreezeAcctService extends SingleTemplate<AcctFreezeBatchRequest, Integer> {
    @Resource
    private AcctInfoMapper acctInfoMapper;


    protected Integer callInner(CommonRequest<AcctFreezeBatchRequest> request) throws  Exception{
        // 1、判断账户号是否存在 && 账户状态是否正常
        AcctInfo acctInfo = acctInfoMapper.selectByPrimaryKey(request.getRequest().getAcctCode());
        if(acctInfo!=null && "00".equals(acctInfo.getStat())) {
            // 2、锁行
            acctInfoMapper.selectAcctInfoForUpdate(acctInfo.getAcctCode());
            //3.更新状态
            acctInfo.setStat("01");
            acctInfo.setFrozenDate(new Date());
            acctInfoMapper.updateByPrimaryKey(acctInfo);


        }else{
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "该账户不存在，或者账户不可用。");
        }


        return 0;
    }

}
