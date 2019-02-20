package com.luckwine.trade.integration.service.business.core;

import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctDeductingRequest;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.enums.*;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.date.DateStyle;
import com.luckwine.parent.tools.date.DateUtil;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.pgw.model.enums.PayType;
import com.luckwine.pgw.model.request.PayOrderCreateRequest;
import com.luckwine.pgw.model.response.AsyncPayInfo;
import com.luckwine.trade.dao.TradeCapitalStatementMapper;
import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.enums.PayChannel;
import com.luckwine.trade.handle.remote.acct.AccountConsumeRemoteService;
import com.luckwine.trade.handle.remote.acct.AccountInfoRemoteService;
import com.luckwine.trade.handle.remote.payment.AlipayConsumeRemoteService;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import com.luckwine.trade.model.request.PayBackRequest;
import com.luckwine.trade.model.request.PayOrderRequest;
import com.luckwine.trade.service.trans.PayBackService;
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
public class PayOrderFlow {


    @Autowired
    private TradeCapitalStatementMapper tradeCapitalStatementMapper;

    @Autowired
    private AccountConsumeRemoteService accountConsumeRemoteService;

    @Autowired
    private AccountInfoRemoteService accountInfoRemoteService;

    @Autowired
    private PayBackService payBackService;

    @Autowired
    private AlipayConsumeRemoteService alipayConsumeRemoteService;

    @Value("${env}")
    private String env;

    /**
     * 余额支付-组装资金流水报文
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier initBalanceCapitalStatement(TradeCarrier tradeCarrier) {
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();

        //组装资金流水实体
        TradeCapitalStatement record = new TradeCapitalStatement();
        //======= 单号 ==========
        String capSeq = SequenceUtil.genSequence(SequenceCode.CAPITAL, env);
        record.setCapSeq(capSeq);
        record.setMainOrderCode(tradeMainOrder.getMainOrderCode());
        //======== 资金相关信息 ==========
        record.setCreateTime(new Date());
        record.setAmount(tradeMainOrder.getPayAmount());
        record.setCapModule(CapModule.ACCT.getCode());
        record.setCapSysType(CapSysType.BALANCE.getCode());
        //======== 支付信息 ==========
        record.setPayOper(PayOper.PAY.getCode());
        record.setPayStatus(PayStatus.PAYMENT_WAITING.getCode());
        //======== 获取付款余额账号 =========
        AcctInfo acctInfo = getAccountInfo(tradeCarrier);
        record.setCapAcctCode(acctInfo.getAcctCode());

        //赋值资金流水实体
        orderCarrier.setTradeCapitalStatement(record);
        tradeCarrier.setOrderCarrier(orderCarrier);

        return tradeCarrier;
    }

    /**
     * 支付宝支付-组装资金流水报文
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier initAlipayCapitalStatement(TradeCarrier tradeCarrier) {
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        TradeMainOrder tradeMainOrder = orderCarrier.getTradeMainOrder();

        //组装资金流水实体
        TradeCapitalStatement record = new TradeCapitalStatement();
        //======= 单号 ==========
        String capSeq = SequenceUtil.genSequence(SequenceCode.CAPITAL, env);
        record.setCapSeq(capSeq);
        record.setMainOrderCode(tradeMainOrder.getMainOrderCode());
        //======== 资金相关信息 ==========
        record.setCreateTime(new Date());
        record.setAmount(tradeMainOrder.getPayAmount());
        record.setCapModule(CapModule.PAYMENT.getCode());
        record.setCapSysType(CapSysType.ALIPAY.getCode());
        //======== 支付信息 ==========
        record.setPayOper(PayOper.PAY.getCode());
        record.setPayStatus(PayStatus.PAYMENT_WAITING.getCode());

        //赋值资金流水实体
        orderCarrier.setTradeCapitalStatement(record);
        tradeCarrier.setOrderCarrier(orderCarrier);

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
        TradeCapitalStatement record = tradeCarrier.getOrderCarrier().getTradeCapitalStatement();
        //插入资金流水信息
        int count = tradeCapitalStatementMapper.insert(record);
        if (count <= 0)
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(),
                    "主单号[" + record.getMainOrderCode() + "]生成资金流水异常");

        return tradeCarrier;
    }

    /**
     * 更新资金流水信息
     *
     * @param record
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int updateCapitalStatement(TradeCapitalStatement record) {
        //更新资金流水信息
        int count = tradeCapitalStatementMapper.updateByPrimaryKeySelective(record);
        if (count <= 0)
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(),
                    "主单号[" + record.getMainOrderCode() + "]更新资金流水异常");

        return count;
    }

    /**
     * 余额支付
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier accountConsume(TradeCarrier tradeCarrier) {
        //调用消费账户请求
        CommonRequest<AcctDeductingRequest> remoteRequest = new CommonRequest<>();
        //当前请求
        CommonRequest<PayOrderRequest> currentRequest = tradeCarrier.getRequest();
        //资金流水
        TradeCapitalStatement tradeCapitalStatement = tradeCarrier.getOrderCarrier().getTradeCapitalStatement();

        //组装报文
        BeanUtils.copyProperties(currentRequest, remoteRequest);
        AcctDeductingRequest acctDeductingRequest = new AcctDeductingRequest();
        acctDeductingRequest.setPayerAcctCode(tradeCapitalStatement.getCapAcctCode());
        acctDeductingRequest.setRequestSeq(tradeCapitalStatement.getCapSeq());
        acctDeductingRequest.setExtTrsSeq(tradeCapitalStatement.getMainOrderCode());
        acctDeductingRequest.setSummary("余额支付-出账");
        acctDeductingRequest.setTrsAmount(tradeCapitalStatement.getAmount());
        remoteRequest.setRequest(acctDeductingRequest);
        //余额账户消费
        CommonResponse<TransRes> res = accountConsumeRemoteService.call(remoteRequest);

        //========================================
        //========== 支付下单的响应信息 ==========
        //========================================
        String msg = "";
        if (ResponseCodeConstant.SUCCESS.getResponseCode().equals(res.getCode())) {
            tradeCapitalStatement.setCapBackCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            tradeCapitalStatement.setCapBackDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        } else {
            msg = "余额支付异常,资金流水号：" + tradeCapitalStatement.getCapSeq() + ",账户号：" + tradeCapitalStatement.getCapAcctCode();
            tradeCapitalStatement.setPayTime(new Date());
            tradeCapitalStatement.setPayStatus(PayStatus.PAYMENT_FAIL.getCode());
            tradeCapitalStatement.setCapBackCode(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
            tradeCapitalStatement.setCapBackDesc(msg);
        }

        //更新余额支付后的资金流水响应码
        updateCapitalStatement(tradeCapitalStatement);

        //余额支付异常抛出 --> 结束流程
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(res.getCode())) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), msg);
        }

        //========================================
        //========== 支付回调的返回信息 ==========
        //========================================
        //支付返回流水信息，在支付回调时候才更新
        tradeCapitalStatement.setCapBackSeq(res.getResponse().getAcctOrderno());
        tradeCapitalStatement.setPayTime(new Date());
        tradeCapitalStatement.setPayStatus(PayStatus.PAYMENT_SUCCEED.getCode());

        //资金流水放入载体，待支付回调
        tradeCarrier.getOrderCarrier().setTradeCapitalStatement(tradeCapitalStatement);

        return tradeCarrier;
    }

    /**
     * 支付宝消费
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier alipayConsume(TradeCarrier tradeCarrier) {
        //本地对象
        CommonRequest<PayOrderRequest> commonRequest = tradeCarrier.getRequest();
        PayOrderRequest payOrderRequest = commonRequest.getRequest();
        TradeMainOrder tradeMainOrder = tradeCarrier.getOrderCarrier().getTradeMainOrder();
        TradeCapitalStatement tradeCapitalStatement = tradeCarrier.getOrderCarrier().getTradeCapitalStatement();
        //远程对象
        CommonRequest<PayOrderCreateRequest> remoteRequest = new CommonRequest<>();
        BeanUtils.copyProperties(commonRequest, remoteRequest);
        //请求支付宝实体
        PayOrderCreateRequest payOrderCreateRequest = new PayOrderCreateRequest();
        payOrderCreateRequest.setAmount(tradeMainOrder.getPayAmount());   //主单支付金额
        payOrderCreateRequest.setSubject("酒缘网_" + TransType.valueOf(tradeMainOrder.getTransType()).getDesc());
        payOrderCreateRequest.setBody("酒缘网平台的支付宝交易业务");
        if (PayChannel.QRPay.equals(payOrderRequest.getPayChannle()))
            payOrderCreateRequest.setPayType(PayType.ALIPAY_QR_CODE);   //二维码支付宝
        else
            payOrderCreateRequest.setPayType(PayType.ALIPAY_PAGE);  //PC版支付宝
        payOrderCreateRequest.setOrderNo(tradeMainOrder.getMainOrderCode());   //主单号
        payOrderCreateRequest.setCapSeq(tradeCapitalStatement.getCapSeq());  //订单资金流水
        payOrderCreateRequest.setUserAccount(tradeMainOrder.getBuyLoginAccount());  //登录账号
        remoteRequest.setRequest(payOrderCreateRequest);

        //支付宝支付
        CommonResponse<AsyncPayInfo> asyncPayInfo = alipayConsumeRemoteService.call(remoteRequest);

        //========================================
        //========== 支付下单的响应信息 ==========
        //========================================
        //返回结果
        String msg = "";
        if (ResponseCodeConstant.SUCCESS.getResponseCode().equals(asyncPayInfo.getCode())) {
            tradeCarrier.getOrderCarrier().setPayBody(asyncPayInfo.getResponse().getBody());
            tradeCapitalStatement.setCapBackCode(ResponseCodeConstant.SUCCESS.getResponseCode());
            tradeCapitalStatement.setCapBackDesc(ResponseCodeConstant.SUCCESS.getResponseDesc());
        } else {
            msg = "支付宝下单异常,资金流水号：" + tradeCapitalStatement.getCapSeq() + ",主单号：" + tradeMainOrder.getMainOrderCode();
            tradeCapitalStatement.setCapBackCode(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
            tradeCapitalStatement.setCapBackDesc(msg);
        }

        //更新余额支付后的资金流水响应码
        updateCapitalStatement(tradeCapitalStatement);

        //支付宝下单异常抛出
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(asyncPayInfo.getCode())) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), msg);
        }

        return tradeCarrier;
    }

    /**
     * 余额支付成功-发起支付回调
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier accountPayBack(TradeCarrier tradeCarrier) {
        //发起支付回调的请求
        CommonRequest<PayBackRequest> remoteRequest = new CommonRequest<>();
        //当前请求
        CommonRequest<PayOrderRequest> currentRequest = tradeCarrier.getRequest();
        //资金流水
        TradeCapitalStatement tradeCapitalStatement = tradeCarrier.getOrderCarrier().getTradeCapitalStatement();

        //组装报文
        BeanUtils.copyProperties(currentRequest, remoteRequest);
        PayBackRequest payBackRequest = new PayBackRequest();
        payBackRequest.setAmount(tradeCapitalStatement.getAmount());
        payBackRequest.setCapAcctCode(tradeCapitalStatement.getCapAcctCode());
        payBackRequest.setCapBackSeq(tradeCapitalStatement.getCapBackSeq());
        payBackRequest.setCapSeq(tradeCapitalStatement.getCapSeq());
        payBackRequest.setMainOrderCode(tradeCapitalStatement.getMainOrderCode());
        payBackRequest.setPayStatus(PayStatus.PAYMENT_SUCCEED);
        payBackRequest.setPayTime(DateUtil.DateToString(tradeCapitalStatement.getPayTime(), DateStyle.YYYY_MM_DD_HH_MM_SS));
        remoteRequest.setRequest(payBackRequest);

        //余额支付回调
        CommonResponse<Boolean> res = payBackService.payBack(remoteRequest);
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(res.getCode())) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(),
                    "余额支付回调异常，cap_seq:" + payBackRequest.getCapSeq() + "，主单号:" + payBackRequest.getMainOrderCode());
        }

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
        CommonRequest<PayOrderRequest> currentRequest = tradeCarrier.getRequest();

        //组装报文
        BeanUtils.copyProperties(currentRequest, remoteRequest);
        remoteRequest.setPageNo(1);
        remoteRequest.setPageSize(1);
        AcctInfoPageRequest acctInfoPageRequest = new AcctInfoPageRequest();
        acctInfoPageRequest.setLoginAccount(currentRequest.getRequest().getBuyLoginAccount());
        remoteRequest.setRequest(acctInfoPageRequest);

        //查询账户信息
        CommonQueryPageResponse<List<AcctInfo>> res = accountInfoRemoteService.call(remoteRequest);
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(res.getCode())) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(),
                    "查询不到余额账号,登录号:" + currentRequest.getRequest().getBuyLoginAccount());
        }
        return res.getResponse().get(0);
    }


}
