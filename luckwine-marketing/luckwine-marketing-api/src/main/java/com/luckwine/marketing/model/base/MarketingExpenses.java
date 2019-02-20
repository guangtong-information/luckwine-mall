package com.luckwine.marketing.model.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "marketing_expenses")
public class MarketingExpenses implements Serializable {

    // 营销交易流水号
    @Id
    private String marketOrderno;

    // 外部订单号-主单号
    private String extTrsSeq;

    // 请求流水号-资金流水号
    private String requestSeq;

    // 交易时间
    private Date createTime;

    // 方案id
    private String schemeId;

    // 券号
    private String couponNum;

    // 登陆账号
    private String userAccount;

    // 优惠金额/折扣
    private BigDecimal discountAmount;

    // 0:成功 1:冲正
    private String status;

}