package com.luckwine.acct.handle.local;

import com.luckwine.acct.dao.AcctAbilityMapper;
import com.luckwine.acct.dao.AcctInfoMapper;
import com.luckwine.acct.dao.ExpensesDetailMapper;
import com.luckwine.acct.model.base.AcctAbility;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.base.ExpensesDetail;
import com.luckwine.acct.model.request.AcctDeductingRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.DataBaseTemplate;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author barryng
 * @date 2018-10-14 14:34
 */
@Service
public class AcctDeductingService extends DataBaseTemplate<AcctDeductingRequest, TransRes> {

    @Resource
    AcctInfoMapper acctInfoMapper;

    @Resource
    AcctAbilityMapper acctAbilityMapper;

    @Resource
    ExpensesDetailMapper expensesDetailMapper;

    @Override
    protected TransRes callInner(CommonRequest<AcctDeductingRequest> request) throws Exception {
        // 1、判断账户号是否存在 && 账户状态是否正常
        AcctInfo acctInfo = acctInfoMapper.selectByPrimaryKey(request.getRequest().getPayerAcctCode());
        if (acctInfo != null && "00".equals(acctInfo.getStat())) {
            // 2、判断余额是否够用
            if (acctInfo.getAvailBal().compareTo(request.getRequest().getTrsAmount()) >= 0) {
                // 3、校验账户能力
                // TODO-解决: 无法判断该请求是『消费』还是『提现』，所以无法完整的判断账户能力，暂以判断『消费』能力先实现逻辑
                Example example = new Example(AcctAbility.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("acctCode", acctInfo.getAcctCode());
                criteria.andEqualTo("abilityCode", request.getRequest().getAbilityCode().getCode());
                List<AcctAbility> acctAbilityList = acctAbilityMapper.selectByExample(example);
                if (acctAbilityList.size() == 0) {
                    throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "该账户没有『" + request.getRequest().getAbilityCode().getDesc() + "』能力。");
                }

                // 出账逻辑：
                TransRes transRes = this.doDeducting(acctInfo, request);
                return transRes;
            } else {
                throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "该账户余额不足。");
            }
        } else {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "该账户不存在，或者账户不可用。");
        }
    }

    /**
     * 出账逻辑
     *
     * @param acctInfo
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    protected TransRes doDeducting(AcctInfo acctInfo, CommonRequest<AcctDeductingRequest> request) throws Exception {
        // 1、锁行
        acctInfoMapper.selectAcctInfoForUpdate(acctInfo.getAcctCode());

        // 2、扣减金额
        acctInfoMapper.deductingAmount(acctInfo.getAcctCode(), request.getRequest().getTrsAmount());

        // 3、生成流水号
        String acctOrderNumber = SequenceUtil.genSequence(SequenceCode.EXPENSE, "1");

        // 4、记录收支明细
        ExpensesDetail expensesDetail = new ExpensesDetail();
        expensesDetail.setAcctOrderno(acctOrderNumber);
        expensesDetail.setRequestSeq(request.getRequest().getRequestSeq());
        expensesDetail.setAcctCode(acctInfo.getAcctCode());
        expensesDetail.setExpenseType("-");
        expensesDetail.setTotalBal(acctInfo.getAvailBal().subtract(request.getRequest().getTrsAmount()));
        expensesDetail.setTrsAmount(request.getRequest().getTrsAmount());
        // TODO-解决: 无法判断『消费』还是『提现』
        expensesDetail.setTransType(request.getRequest().getAbilityCode().getDesc());
        expensesDetail.setSummary(request.getRequest().getSummary());
        expensesDetail.setExtTrsSeq(request.getRequest().getExtTrsSeq());
        expensesDetailMapper.insertSelective(expensesDetail);

        // 5、生成返回结果对象
        TransRes transRes = new TransRes();
        transRes.setAcctCode(acctInfo.getAcctCode());
        transRes.setAcctName(acctInfo.getAcctName());
        transRes.setAcctOrderno(acctOrderNumber);
        transRes.setAcctTypeCode(acctInfo.getAcctTypeCode());
        transRes.setAvailBal(acctInfo.getAvailBal());
        transRes.setExtTrsSeq(request.getRequest().getExtTrsSeq());
        transRes.setLoginAccount(acctInfo.getLoginAccount());
        transRes.setRequestSeq(request.getRequest().getRequestSeq());

        return transRes;
    }
}
