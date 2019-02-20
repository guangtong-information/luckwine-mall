package com.luckwine.pgw.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.pgw.config.AlipayConfig;
import com.luckwine.pgw.dao.PayOrderMapper;
import com.luckwine.pgw.model.enums.PayType;
import com.luckwine.pgw.model.request.PayOrderCreateRequest;
import com.luckwine.pgw.model.response.AsyncPayInfo;
import com.luckwine.pgw.model.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello");
    }

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {
        return "ok 支付成功" + System.currentTimeMillis();
    }

    /*
     *
     * 买家账号pyshor4270@sandbox.com
     * 登录密码111111
     * 支付密码111111
     * */
    @RequestMapping(value = "/payorder", method = RequestMethod.GET)
    public ResponseEntity alipay(@RequestParam("payType") String payType) {
        CommonRequest<PayOrderCreateRequest> commonRequest = new CommonRequest();
        PayOrderCreateRequest request = new PayOrderCreateRequest();
        request.setAmount(new BigDecimal("10" + RandomStringUtils.randomNumeric(1)));
        request.setSubject("标题page_" + RandomStringUtils.randomAlphabetic(4));
        request.setBody("描述" + RandomStringUtils.randomAlphabetic(4));
        request.setPayType(PayType.valueOf(payType));
        request.setOrderNo(SequenceUtil.genSequence(SequenceCode.MAINORDER, "0"));
        request.setCapSeq(SequenceUtil.genSequence(SequenceCode.CAPITAL, "0"));
        request.setUserAccount("zhangsan");
        commonRequest.setRequest(request);
        CommonResponse<AsyncPayInfo> commonResponse = payOrderService.asyncPayCreate(commonRequest);

        if (ResponseCodeConstant.SUCCESS.getResponseCode().equals(commonResponse.getCode())) {
            return ResponseEntity.ok(commonResponse.getResponse().getBody());
        } else {
            return ResponseEntity.badRequest().body(commonResponse);
        }
    }


    /*
     *   WAIT_BUYER_PAY（交易创建，等待买家付款）、
     *  TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     *  TRADE_SUCCESS（交易支付成功）、
     *  TRADE_FINISHED（交易结束，不可退款）
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity query(@RequestParam("outTradeNo") String outTradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
        Map<String, String> param = new HashMap<>();
        param.put("out_trade_no", outTradeNo);
        queryRequest.setBizContent(JSON.toJSONString(param));
        AlipayTradeQueryResponse response = alipayClient.execute(queryRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }


    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public ResponseEntity cancel(@RequestParam("outTradeNo") String outTradeNo) throws AlipayApiException {
        //
        // 提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】
        AlipayTradeCancelRequest cancelRequest = new AlipayTradeCancelRequest();
        Map<String, String> param = new HashMap<>();
        param.put("out_trade_no", outTradeNo);
        cancelRequest.setBizContent(JSON.toJSONString(param));
        AlipayTradeCancelResponse response = alipayClient.execute(cancelRequest);
        if (response.isSuccess()) {
            if (StringUtils.isBlank(response.getAction())) {
                log.info("没有发生任何操作 本次撤销失败");
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /*
     *  yyyy-MM-dd：2016-04-05
     * */
    @RequestMapping(value = "/billDownload", method = RequestMethod.GET)
    public ResponseEntity billDownload(@RequestParam("billDate") String billDate) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        Map<String, String> param = new HashMap<>();
        param.put("bill_type", "trade");
        param.put("bill_date", billDate);
        request.setBizContent(JSON.toJSONString(param));
        AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }


}
