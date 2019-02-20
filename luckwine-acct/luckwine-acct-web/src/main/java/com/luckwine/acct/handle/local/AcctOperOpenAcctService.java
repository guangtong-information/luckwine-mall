package com.luckwine.acct.handle.local;

import com.luckwine.acct.dao.AcctAbilityMapper;
import com.luckwine.acct.dao.AcctInfoMapper;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctOperRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author hao
 *
 */
@Transactional
@Service
public class AcctOperOpenAcctService extends SingleTemplate<AcctOperRequest, String> {

    @Resource
    private AcctInfoMapper acctInfoMapper;
    @Resource
    private AcctAbilityMapper acctAbilityMapper;
    @Resource
    private AcctAbilityBatchConfigService acctAbilityBatchConfigService;


    @Override
    protected String callInner(CommonRequest<AcctOperRequest> request) throws Exception {
        //生成acctCode
        String acctCode = SequenceUtil.genSequence(SequenceCode.ACCT, "0");

        AcctOperRequest acctOperRequest = request.getRequest();

        AcctInfo acctInfo = new AcctInfo();
        BeanUtils.copyProperties(acctOperRequest,acctInfo);
        acctInfo.setAcctCode(acctCode);

        acctInfoMapper.insertSelective(acctInfo);

        //保存账户能力
        List<String> abilityCodeList = acctOperRequest.getAbilityCodeList();
        if (abilityCodeList != null && !abilityCodeList.isEmpty()) {
            List<String> acctCodeList = Arrays.asList(acctCode);
            acctAbilityMapper.batchInsert(acctCodeList, request.getRequest().getAbilityCodeList());
        }

        return acctCode;
    }
}
