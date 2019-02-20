package com.luckwine.marketing.model.request.coupon;

import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
public class MarketRequestSeq  {

    /**
     * 记录到营销流水表中
     */
    @NotNull(message = "资金流水号不能为空")
    private String requestSeq;

    @NotNull(message = "优惠券号不能为空")
    private String couponNum;

    /**
     * 多个商品共用一张优惠券
     */
    @NotNull(message = "商品SkuID列表不能为空")
    private List<Long> skuIdSubList;



}
