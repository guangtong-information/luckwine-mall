package com.luckwine.marketing.model.response;

import com.luckwine.marketing.model.base.MarketingCoupon;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
public class OrderAvailableCouponResp extends MarketingCoupon {

    /**
     * 多个商品共享一张优惠券
     */
    private List<Long> skuIdSubList;

}
