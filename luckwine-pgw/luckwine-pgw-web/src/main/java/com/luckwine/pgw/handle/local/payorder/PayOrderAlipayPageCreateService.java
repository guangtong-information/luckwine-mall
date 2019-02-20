package com.luckwine.pgw.handle.local.payorder;

import com.alibaba.fastjson.JSON;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.TransTemplate;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.pgw.config.AlipayConfig;
import com.luckwine.pgw.dao.PayOrderMapper;
import com.luckwine.pgw.handle.remote.alipay.AlipayPageCreateService;
import com.luckwine.pgw.model.base.PayOrder;
import com.luckwine.pgw.model.enums.OrderSyncStatus;
import com.luckwine.pgw.model.enums.PayOrderStatus;
import com.luckwine.pgw.model.enums.PayType;
import com.luckwine.pgw.model.enums.Supplier;
import com.luckwine.pgw.model.request.PayOrderCreateRequest;
import com.luckwine.pgw.model.response.AsyncPayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayOrderAlipayPageCreateService extends TransTemplate<PayOrderCreateRequest, AsyncPayInfo, AlipayTradePagePayRequest, CommonResponse<AlipayTradePagePayResponse>, AlipayTradePagePayResponse>  {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private AlipayPageCreateService alipayPageCreateService;

    @Value("${orderEnv}")
    private String orderEnv;


    @Override
    protected CommonResponse<AlipayTradePagePayResponse> call(CommonRequest<AlipayTradePagePayRequest> req) {
        return alipayPageCreateService.call(req);
    }


    @Override
    protected AlipayTradePagePayRequest transformRequest(PayOrderCreateRequest request) {
        if (PayType.ALIPAY_PAGE != request.getPayType()) {
            throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), ResponseCodeConstant.SYS_EXCEPTION.getResponseDesc());
        }
        String payOrderNo = SequenceUtil.genSequence(SequenceCode.PAYMENT, orderEnv);

        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderNo(payOrderNo);
        payOrder.setOrderNo(request.getOrderNo());
        payOrder.setAmount(request.getAmount());
        payOrder.setCapSeq(request.getCapSeq());
        payOrder.setUserAccount(request.getUserAccount());
        payOrder.setSubject(request.getSubject());
        payOrder.setBody(request.getBody());
        payOrder.setPayType(request.getPayType().name());
        payOrder.setOrderSyncStatus(OrderSyncStatus.SYNC_WAIT.name());
        payOrder.setSupplierCode(Supplier.ALIPAY.name());
        payOrder.setStatus(PayOrderStatus.CREATE.name());
        payOrder.setCreateTime(new Date());
        int count = payOrderMapper.insertSelective(payOrder);
        if (count < 1) {
            throw new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(), ResponseCodeConstant.DB_EXCEPTION.getResponseDesc());
        }


        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //支付成功后 跳转的地址
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        //支付成功后 回调的地址
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());

        Map<String, Object> bizContent = new HashMap<>();

        //销售产品码  注：目前仅支持FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        //pgw 支付单号
        bizContent.put("out_trade_no", payOrderNo);
        //订单总金额
        bizContent.put("total_amount", request.getAmount());
        //订单标题
        bizContent.put("subject", request.getSubject());
        //订单描述
        bizContent.put("body", request.getBody());

        bizContent.put("timeout_express", alipayConfig.getTimeoutExpress());

        alipayRequest.setBizContent(JSON.toJSONString(bizContent));//填充业务参数
        return alipayRequest;
    }

    @Override
    protected AsyncPayInfo transformerResponse(AlipayTradePagePayResponse response, CommonRequest<PayOrderCreateRequest> req) {
        AsyncPayInfo asyncPayInfo = new AsyncPayInfo();
        asyncPayInfo.setBody(response.getBody());
        return asyncPayInfo;
    }
}
