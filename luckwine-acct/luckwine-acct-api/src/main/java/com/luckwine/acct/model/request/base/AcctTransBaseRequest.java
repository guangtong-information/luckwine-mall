package com.luckwine.acct.model.request.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 账户操作基础类
 */
@Data
@ToString(callSuper = true)
public class AcctTransBaseRequest extends BaseRequest {

    /**
     * 账户请求流水号（订单模块传入）
     */
    @NotNull(message = "请求流水不能为空")
    @Length(max = 32, message = "请求流水长度不能小于32位")
    private String requestSeq;

    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    @DecimalMin(value = "0.01", message = "金额不能小于0.01")
    private BigDecimal trsAmount;

    /**
     * summary 交易摘要
     */
    private String summary;

    /**
     * 外部系统订单号(订单模块的主单号)
     */
    @NotNull(message = "外部系统订单号(订单模块的主单号)不能为空")
    private String extTrsSeq;

}

