package com.luckwine.marketing.model.response;

import com.luckwine.marketing.model.base.MarketingExpenses;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
public class UserMarketingCouponResq extends MarketingExpenses {

    /**
     * 多个商品共享一张优惠券
     */
    @NotNull(message = "商品集合")
    private List<Long> skuIdSubList;

}
