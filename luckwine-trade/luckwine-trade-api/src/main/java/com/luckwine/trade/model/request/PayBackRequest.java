package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.enums.PayStatus;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 支付回调
 * Created by winlone on 2018/9/21.
 */
@Data
@ToString(callSuper = true)
public class PayBackRequest extends BaseRequest {
    /**
     * 订单资金流水（订单模块的trade_capital_statement表cap_seq资金流水号）
     */
    @NotBlank(message = "订单资金流水号不能为空")
    private String capSeq;

    /**
     * 外部资金流水（支付模块的pay_order表supplier_order_no供应商订单号）
     */
    @NotBlank(message = "回调资金流水号不能为空")
    private String capBackSeq;

    /**
     * 外部资金账户号（支付模块的pay_order表supplier_user_account供应商对应用户账号）
     */
    @NotBlank(message = "外部资金账户号不能为空")
    private String capAcctCode;

    /**
     * 主订单号（订单模块的trade_main_order表main_order_code主单号码）
     */
    @NotBlank(message = "主订单号不能为空")
    private String mainOrderCode;

    /**
     * 支付金额
     */
    @DecimalMin(value = "0.01", message = "支付金额不能小于0.01")
    private BigDecimal amount;

    /**
     * 支付状态
     */
    @NotNull(message = "支付状态不能为空")
    private PayStatus payStatus;

    /*  支付时间 */
    @NotNull(message = "支付时间不能为空")
    private String payTime;

}
