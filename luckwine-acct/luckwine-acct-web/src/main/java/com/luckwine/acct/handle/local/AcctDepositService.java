package com.luckwine.acct.handle.local;

import com.luckwine.acct.dao.AcctAbilityMapper;
import com.luckwine.acct.dao.AcctInfoMapper;
import com.luckwine.acct.dao.ExpensesDetailMapper;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.base.ExpensesDetail;
import com.luckwine.acct.model.request.AcctDepositRequest;
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

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class AcctDepositService extends DataBaseTemplate<AcctDepositRequest, TransRes> {

    @Resource
    AcctInfoMapper acctInfoMapper;

    @Resource
    AcctAbilityMapper acctAbilityMapper;

    @Resource
    ExpensesDetailMapper expensesDetailMapper;

    @Override
    protected TransRes callInner(CommonRequest<AcctDepositRequest> request) throws Exception {
        AcctInfo acctInfo = acctInfoMapper.selectByPrimaryKey(request.getRequest().getPayeeAcctCode());
        return doDeposit(acctInfo, request);
    }

    private boolean check(CommonRequest<AcctDepositRequest> request, AcctInfo acctInfo) {
        //判断账户是否存在，是否可用，存款不能大于余额限额，该账户号有没有入账能力
        if (acctInfo != null && acctInfo.getStat().equals("00")
                && acctInfo.getAvailBal().add(request.getRequest().getTrsAmount()).compareTo(acctInfo.getBalLimit()) <= 0) {
            List<Integer> intList = acctAbilityMapper.selectDepositByAcctCode(request.getRequest().getPayeeAcctCode());
            if (intList.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private TransRes doDeposit(AcctInfo acctInfo, CommonRequest<AcctDepositRequest> request) {
        //先对账户号做检查，是否可用，存款不能大于余额限额，该账户号有没有入账能力
        if (check(request, acctInfo)) {
            //锁行
            acctInfoMapper.selectAcctInfoForUpdate(acctInfo.getAcctCode());

            //增加存款金额
            acctInfo.setAvailBal(acctInfo.getAvailBal().add(request.getRequest().getTrsAmount()));
            acctInfoMapper.updateByPrimaryKey(acctInfo);

            //ExpensesDetail实体
            //生成账户流水号
            String acctOrderno = SequenceUtil.genSequence(SequenceCode.EXPENSE, "1");
            ExpensesDetail expensesDetail = new ExpensesDetail();
            expensesDetail.setAcctOrderno(acctOrderno);
            expensesDetail.setRequestSeq(request.getRequest().getRequestSeq());
            expensesDetail.setAcctCode(acctInfo.getAcctCode());
            expensesDetail.setExpenseType("+");
            expensesDetail.setTotalBal(acctInfo.getAvailBal());
            expensesDetail.setTrsAmount(request.getRequest().getTrsAmount());
            expensesDetail.setTransType("充值");
            Date now = new Date();
            expensesDetail.setCreateTime(now);
            expensesDetail.setUpdateTime(now);
            expensesDetail.setSummary(request.getRequest().getSummary());
            expensesDetail.setExtTrsSeq(request.getRequest().getExtTrsSeq());
            expensesDetailMapper.insert(expensesDetail);

            //TransRes实体
            TransRes transRes = new TransRes();
            transRes.setAcctCode(acctInfo.getAcctCode());
            transRes.setAcctName(acctInfo.getAcctName());
            transRes.setAcctOrderno(acctOrderno);
            transRes.setAcctTypeCode(acctInfo.getAcctTypeCode());
            transRes.setAvailBal(acctInfo.getAvailBal());
            transRes.setExtTrsSeq(request.getRequest().getExtTrsSeq());
            transRes.setLoginAccount(acctInfo.getLoginAccount());
            transRes.setRequestSeq(request.getRequest().getRequestSeq());

            return transRes;
        } else {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "当前用户没有充值能力");
        }
    }
}
