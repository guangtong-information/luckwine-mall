package com.luckwine.trade.controller;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.enums.AppName;
import com.luckwine.parent.entitybase.enums.ChannelCode;
import com.luckwine.parent.entitybase.enums.OperLevel;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.trade.enums.PayChannel;
import com.luckwine.trade.model.request.PayOrderRequest;
import com.luckwine.trade.model.response.PayOrderRes;
import com.luckwine.trade.service.trans.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping(value = "payorder",method = RequestMethod.GET)
    public ResponseEntity index(@ModelAttribute PayOrderRequest payOrderRequest) throws IOException {
        CommonRequest request = new CommonRequest();
        request.setAppName(AppName.TRADE.getCode());
        request.setChannelCode(ChannelCode.PORTALWEB.getCode());
        request.setOperLevel(OperLevel.DEFAULT.getCode());
        request.setTraceId(SequenceUtil.genSequence(SequenceCode.TRACEID, "1"));
        request.setRequest(payOrderRequest);
        //下单工作流
        CommonResponse<PayOrderRes> commonResponse = payOrderService.alipayPay(request);
        System.out.println("支付下单结果：" + commonResponse);

        if (ResponseCodeConstant.SUCCESS.getResponseCode().equals(commonResponse.getCode())) {
            return ResponseEntity.ok(commonResponse.getResponse().getBody());
        } else {
            return ResponseEntity.badRequest().body(commonResponse);
        }
    }

}
