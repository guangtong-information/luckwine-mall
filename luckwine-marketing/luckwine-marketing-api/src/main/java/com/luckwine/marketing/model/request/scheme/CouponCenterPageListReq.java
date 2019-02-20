package com.luckwine.marketing.model.request.scheme;

import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.parent.entitybase.request.BaseRequest;

public class CouponCenterPageListReq extends MarketingScheme {

    /**
     * 是否生成优惠劵 0.未生成 1.已生MarketRequestSeq成
     */
    private Integer genCouponInt;

    private String createStartDate;

    private String createEndDate;


    public Integer getGenCouponInt() {
        return genCouponInt;
    }

    public void setGenCouponInt(Integer genCouponInt) {
        this.genCouponInt = genCouponInt;
    }

    public String getCreateStartDate() {
        return createStartDate;
    }

    public void setCreateStartDate(String createStartDate) {
        this.createStartDate = createStartDate;
    }

    public String getCreateEndDate() {
        return createEndDate;
    }

    public void setCreateEndDate(String createEndDate) {
        this.createEndDate = createEndDate;
    }
}
