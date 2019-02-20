package com.luckwine.marketing.model.response;

import com.luckwine.marketing.model.base.MarketingScheme;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class CouponCenterPageListResp extends MarketingScheme {

    @NotNull(message = "优惠券未领取总数")
    private Integer noGetTotal;

}
