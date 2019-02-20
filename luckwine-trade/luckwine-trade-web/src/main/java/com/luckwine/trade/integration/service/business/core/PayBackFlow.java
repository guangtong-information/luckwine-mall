package com.luckwine.trade.integration.service.business.core;

import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctDepositRequest;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.enums.*;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.trade.dao.TradeCapitalStatementMapper;
import com.luckwine.trade.dao.TradeMainOrderMapper;
import com.luckwine.trade.dao.TradeSubOrderMapper;
import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.entity.TradeSubOrder;
import com.luckwine.trade.handle.remote.acct.AccountDepositRemoteService;
import com.luckwine.trade.handle.remote.acct.AccountInfoRemoteService;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.model.request.PayBackRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 支付订单
 * Created by Winlone on 2018/9/20.
 */
@Service
@Slf4j
public class PayBackFlow {

    @Autowired
    private TradeCapitalStatementMapper tradeCapitalStatementMapper;

    @Autowired
    private TradeMainOrderMapper tradeMainOrderMapper;

    @Autowired
    private TradeSubOrderMapper tradeSubOrderMapper;

    @Autowired
    private AccountDepositRemoteService accountDepositRemoteService;

    @Autowired
    private AccountInfoRemoteService accountInfoRemoteService;

    @Value("${recAcct}")
    private String recAcct;

    @Value("${env}")
    private String env;

    /**
     * 更新主单、子单、资金流水单状态
     * @param tradeCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TradeCarrier updateAllStatus(TradeCarrier tradeCarrier) {
        CommonRequest<PayBackRequest> request = tradeCarrier.getRequest();
        PayBackRequest payBackRequest = request.getRequest();
        TradeCapitalStatement tradeCapitalStatement = tradeCarrier.getOrderCarrier().getTradeCapitalStatement();

        //更新主单状态
        updateMainOrderStatus(payBackRequest.getMainOrderCode(), payBackRequest.getPayStatus().getCode());
        //更新子单状态
        updateSubOrderStatus(payBackRequest.getMainOrderCode(), payBackRequest.getPayStatus().getCode());
        //更新资金流水状态
        updateCapitalStatement(tradeCapitalStatement);

        //如果支付状态非成功，则不做入账处理
        if (!PayStatus.PAYMENT_SUCCEED.equals(payBackRequest.getPayStatus())) {
            throw new CommonException(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), "资金流水[" + payBackRequest.getCapSeq() + "]支付回调状态:"
                    + payBackRequest.getPayStatus().getDesc() + ",支付异常不做收款操作");
        }

        return tradeCarrier;
    }

    /**
     * 更新主订单状态
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int updateMainOrderStatus(String mainOrderCode, String payStatus) {
        //更改主单状态
        TradeMainOrder record = new TradeMainOrder();
        record.setMainOrderCode(mainOrderCode);
        record.setOrderStatus(payStatus);
        return tradeMainOrderMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新子订单状态
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int updateSubOrderStatus(String mainOrderCode, String payStatus) {
        //更改子单状态
        TradeSubOrder record = new TradeSubOrder();
        record.setMainOrderCode(mainOrderCode);
        record.setOrderStatus(payStatus);
        return tradeSubOrderMapper.updateByMainCodeSelective(record);
    }

    /**
     * 支付回调-更新资金流水信息
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int updateCapitalStatement(TradeCapitalStatement tradeCapitalStatement) {
        //更新资金流水数据
        int count = tradeCapitalStatementMapper.updateByPrimaryKey(tradeCapitalStatement);
        if (count < 0) {
            log.error("支付回调更新资金流水失败,资金流水:{},支付时间:{},支付状态:{}",
                    tradeCapitalStatement.getCapSeq(), tradeCapitalStatement.getPayTime(), tradeCapitalStatement.getCapBackSeq());
        }

        return count;
    }

    /**
     * 支付回调收款
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier accountDeposit(TradeCarrier tradeCarrier) {
        //调用消费账户请求
        CommonRequest<AcctDepositRequest> remoteRequest = new CommonRequest<>();
        //当前请求
        CommonRequest<PayBackRequest> currentRequest = tradeCarrier.getRequest();
        TradeMainOrder tradeMainOrder = tradeCarrier.getOrderCarrier().getTradeMainOrder();
        //收款资金流水号
        String capSeq = SequenceUtil.genSequence(SequenceCode.CAPITAL, env);

        Date createTime = new Date();

        String payeeAcctCode = recAcct;
        if (TransType.CONSUME.getCode().equals(tradeMainOrder.getTransType())) {
            //消费使用酒缘网收款大账号
            payeeAcctCode = recAcct;
        } else if (TransType.RECHARGE.getCode().equals(tradeMainOrder.getTransType())) {
            //充值是收款到自己账号
            AcctInfo acctInfo = getAccountInfo(tradeCarrier);
            payeeAcctCode = acctInfo.getAcctCode();
        }
        if (payeeAcctCode == null)
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(),
                    tradeMainOrder.getTransType() + "业务的支付回调收款账号" + payeeAcctCode + "获取不了");

        //================= 1.支付回调收款 ===============
        //组装报文
        BeanUtils.copyProperties(currentRequest, remoteRequest);
        AcctDepositRequest acctDeductingRequest = new AcctDepositRequest();
        acctDeductingRequest.setPayeeAcctCode(payeeAcctCode);  //收款账户
        acctDeductingRequest.setRequestSeq(capSeq);
        acctDeductingRequest.setExtTrsSeq(currentRequest.getRequest().getMainOrderCode());
        acctDeductingRequest.setSummary("余额支付-收款");
        acctDeductingRequest.setTrsAmount(tradeMainOrder.getPayAmount());
        remoteRequest.setRequest(acctDeductingRequest);
        //余额账户收款
        CommonResponse<TransRes> res = accountDepositRemoteService.call(remoteRequest);

        //================= 2.返回结果，组装收款资金流水录入报文 ===============
        TradeCapitalStatement recTradeCapitalStatement = new TradeCapitalStatement();
        //======= 主单号 ==========
        recTradeCapitalStatement.setCapSeq(capSeq);
        recTradeCapitalStatement.setMainOrderCode(currentRequest.getRequest().getMainOrderCode());
        //======== 资金相关信息 ==========
        recTradeCapitalStatement.setCreateTime(createTime);
        recTradeCapitalStatement.setAmount(tradeMainOrder.getPayAmount());
        recTradeCapitalStatement.setCapModule(CapModule.ACCT.getCode());
        recTradeCapitalStatement.setCapSysType(CapSysType.BALANCE.getCode());
        //======== 支付信息 ==========
        recTradeCapitalStatement.setPayOper(PayOper.RECEIVE.getCode());
        recTradeCapitalStatement.setCapAcctCode(payeeAcctCode);

        String msg = "";
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(res.getCode())) {
            msg = "支付回调收款异常,账户号:" + payeeAcctCode;
            recTradeCapitalStatement.setPayTime(new Date());
            recTradeCapitalStatement.setPayStatus(PayStatus.PAYMENT_FAIL.getCode());
            recTradeCapitalStatement.setCapBackCode(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
            recTradeCapitalStatement.setCapBackDesc(msg);
        } else {
            recTradeCapitalStatement.setPayTime(new Date());
            recTradeCapitalStatement.setPayStatus(PayStatus.PAYMENT_SUCCEED.getCode());
            recTradeCapitalStatement.setCapBackSeq(res.getResponse().getAcctOrderno());  //返回流水
            recTradeCapitalStatement.setCapBackCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            recTradeCapitalStatement.setCapBackDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        }
        tradeCarrier.getOrderCarrier().setRecTradeCapitalStatement(recTradeCapitalStatement);

        return tradeCarrier;
    }

    /**
     * 插入资金流水
     *
     * @param tradeCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TradeCarrier insertCapitalStatement(TradeCarrier tradeCarrier) {
        TradeCapitalStatement record = tradeCarrier.getOrderCarrier().getRecTradeCapitalStatement();
        //插入资金流水信息
        int count = tradeCapitalStatementMapper.insert(record);
        if (count <= 0)
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(),
                    "主单号[" + record.getMainOrderCode() + "]生成收款资金流水异常");

        return tradeCarrier;
    }

    /**
     * 返回结果
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier converResult(TradeCarrier tradeCarrier) {
        tradeCarrier.setResult(true);
        tradeCarrier.setResultCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        tradeCarrier.setResultDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        return tradeCarrier;
    }

    /**
     * 根据登录号查询账户信息
     *
     * @param tradeCarrier
     * @return
     */
    public AcctInfo getAccountInfo(TradeCarrier tradeCarrier) {
        //调用消费账户请求
        CommonQueryPageRequest<AcctInfoPageRequest> remoteRequest = new CommonQueryPageRequest<>();
        //当前请求
        CommonRequest<PayBackRequest> currentRequest = tradeCarrier.getRequest();
        TradeMainOrder tradeMainOrder = tradeCarrier.getOrderCarrier().getTradeMainOrder();

        //组装报文
        BeanUtils.copyProperties(currentRequest, remoteRequest);
        remoteRequest.setPageNo(1);
        remoteRequest.setPageSize(1);
        AcctInfoPageRequest acctInfoPageRequest = new AcctInfoPageRequest();
        acctInfoPageRequest.setLoginAccount(tradeMainOrder.getBuyLoginAccount());
        remoteRequest.setRequest(acctInfoPageRequest);

        //查询账户信息
        CommonQueryPageResponse<List<AcctInfo>> res = accountInfoRemoteService.call(remoteRequest);
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(res.getCode())) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(),
                    "查询不到余额账号,登录号:" + tradeMainOrder.getBuyLoginAccount());
        }
        return res.getResponse().get(0);
    }
}
