package com.luckwine.pgw.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import com.luckwine.pgw.model.enums.PayType;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 支付单创建
 */
@Data
@ToString(callSuper = true)
public class PayOrderCreateRequest extends BaseRequest {

    /** 订单号 */
    @NotBlank
    private String orderNo;

    /** 资金流水*/
    @NotBlank
    private String capSeq;

    @NotNull
    @DecimalMin(value = "0.01", message = "金额不能小于0.01")
    private BigDecimal amount;

    /** 用户账号 */
    @NotBlank
    private String userAccount;

    /** 支付单标题 */
    private String subject;

    /** 支付单描述 */
    private String body;

    /** 支付方式 */
    @NotNull
    private PayType payType;
}
