package com.luckwine.trade.model.request;

import com.luckwine.trade.enums.PayChannel;
import com.luckwine.trade.model.request.base.TransBaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


/**
 * 下单请求
 * Created by Winlone on  2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class PayOrderRequest extends TransBaseRequest {

    /**
     * 主单号
     */
    @NotBlank(message = "支付的主单号不能为空")
    private String mainOrderCode;

    /**
     * 1.二维码支付：QRPay 2.PC支付：PCPay
     */
    private PayChannel payChannle;
}
