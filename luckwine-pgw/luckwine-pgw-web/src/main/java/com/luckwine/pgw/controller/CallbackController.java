package com.luckwine.pgw.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.luckwine.pgw.config.AlipayConfig;
import com.luckwine.pgw.dao.PayOrderMapper;
import com.luckwine.pgw.model.base.PayOrder;
import com.luckwine.pgw.model.enums.AlipayStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/callback")
public class CallbackController {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private AlipayConfig alipayConfig;


    @RequestMapping(value = "/alipay", method = RequestMethod.POST)
    public ResponseEntity alipay(HttpServletRequest request) throws AlipayApiException {
        Map<String, String[]> params = request.getParameterMap();

        Map<String, String> paramsMap = new HashMap<>();
        for (String key : params.keySet()) {
            paramsMap.put(key, params.get(key)[0]);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, alipayConfig.getPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        log.info("signVerified :{}", signVerified);

        if (signVerified) {
            //支付单号
            String payOrderNo = request.getParameter("out_trade_no");
            //trade_no支付宝订单号
            String supplierOrderNo = request.getParameter("trade_no");
            String totalAmount = request.getParameter("total_amount");
            //付款的支付宝账号
            String supplierUserAccount = request.getParameter("buyer_id");
            //交易状态
            String tradeStatus = request.getParameter("trade_status");
            Date payTime = null;
            try {
                payTime = DateUtils.parseDate(request.getParameter("gmt_payment"), "yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                log.error("日期解析异常", e);
                payTime = new Date();
            }
            log.info("验签成功：tradeStatus:{}, payOrderNo{},supplierOrderNo:{},totalAmount:{}, supplierUserAccount:{},payTime:{}",tradeStatus, payOrderNo, supplierOrderNo, totalAmount, supplierUserAccount, payTime);

            PayOrder payOrder = new PayOrder();
            payOrder.setUpdateTime(new Date());
            payOrder.setAmount(new BigDecimal(totalAmount));
            payOrder.setSupplierOrderNo(supplierOrderNo);
            payOrder.setSupplierUserAccount(supplierUserAccount);
            payOrder.setSupplierOrderStatus(tradeStatus);
            payOrder.setStatus(AlipayStatus.convert(tradeStatus).name());
            payOrder.setPayTime(payTime);

            //TODO 同步订单后更新状态
            //payOrder.setOrderSyncStatus(OrderSyncStatus.SYNC_SUCCESS.name());

            Example example = new Example(PayOrder.class);
            example.createCriteria().andEqualTo("payOrderNo", payOrderNo);

            log.info("TODO: 如果成功， 回调通知 交易模块， 订单支付完成");

            payOrderMapper.updateByExampleSelective(payOrder, example);

        } else {
            log.info("验签失败");
        }
        return ResponseEntity.ok("success");
    }



}
