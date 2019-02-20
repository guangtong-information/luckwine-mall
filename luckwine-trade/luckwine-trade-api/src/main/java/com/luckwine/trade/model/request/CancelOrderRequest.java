package com.luckwine.trade.model.request;

import com.luckwine.trade.model.request.base.TransBaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


/**
 * 取消订单
 * Created by Winlone on  2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class CancelOrderRequest extends TransBaseRequest {

    /**
     * 主单号
     */
    @NotBlank(message = "取消的主单号不能为空")
    private String mainOrderCode;
}
