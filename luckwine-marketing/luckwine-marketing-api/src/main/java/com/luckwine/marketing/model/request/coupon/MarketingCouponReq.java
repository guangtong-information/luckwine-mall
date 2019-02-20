package com.luckwine.marketing.model.request.coupon;

import com.luckwine.marketing.model.base.MarketingCoupon;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
public class MarketingCouponReq  extends MarketingCoupon {


    private String createStartDate;

    private String createEndDate;

    /**
     * 方案名称
     */
    private String schemeName;
    /**
     * 优惠对像
     */
    private String schemeObj;
    /**
     *优惠数值
     */
    private BigDecimal discountAmount;
    /**
     * 生效时间
     */
    private Date effectiveEndtime;


}
