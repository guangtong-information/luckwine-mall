package com.luckwine.trade.model.request;

import com.luckwine.trade.model.request.base.TransBaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 下单请求
 * Created by Winlone on  2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class RechargeOrderRequest extends TransBaseRequest {

    /* 充值金额 */
    @NotNull(message = "充值金额不能为空")
    private BigDecimal amount;
}
