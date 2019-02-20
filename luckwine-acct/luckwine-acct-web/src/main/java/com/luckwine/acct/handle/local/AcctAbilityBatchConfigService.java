package com.luckwine.acct.handle.local;

import com.luckwine.acct.dao.AcctAbilityMapper;
import com.luckwine.acct.model.request.AcctAbilityBatchRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.DataBaseTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 账户查询列表
 * DataBaseTemplate<接口请求实体, 接口返回实体>
 */
@Transactional
@Service
public class AcctAbilityBatchConfigService extends DataBaseTemplate<AcctAbilityBatchRequest, Integer> {

    @Resource
    private AcctAbilityMapper acctAbilityMapper;

    @Override
    protected Integer callInner(CommonRequest<AcctAbilityBatchRequest> request) {
        //先删除配置账户能力
        int delCount = 0;
        int configCount = 0;
        if (request.getRequest().getAcctCodeList() != null) {
            delCount = acctAbilityMapper.deleteByAcctCodeList(request.getRequest().getAcctCodeList());
            //再重新配置账户能力
            if (request.getRequest().getAbilityCodeList() != null)
                configCount = acctAbilityMapper.batchInsert(request.getRequest().getAcctCodeList(), request.getRequest().getAbilityCodeList());
        }
        return configCount;
    }

}
