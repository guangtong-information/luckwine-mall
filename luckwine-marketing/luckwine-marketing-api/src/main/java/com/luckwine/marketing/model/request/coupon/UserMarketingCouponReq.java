package com.luckwine.marketing.model.request.coupon;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
public class UserMarketingCouponReq extends BaseRequest {

    @NotNull(message = "登陆账号不能为空")
    private String loginAccount;

    /**
     * 记录到营销流水表中
     */
    @NotNull(message = "主订单号不能为空")
    private String orderNo;

    /**
     * 一个订单使用多张优惠券
     */
    @NotNull(message = "营销资金流水列表不能为空")
    private List<MarketRequestSeq> marketRequestSeqList;
}
