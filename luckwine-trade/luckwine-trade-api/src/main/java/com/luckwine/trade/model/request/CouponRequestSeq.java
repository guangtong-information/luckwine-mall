package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
public class CouponRequestSeq extends BaseRequest {


    @NotNull(message = "优惠券号不能为空")
    private String couponNum;

    /**
     * 多个商品共用一张优惠券
     */
    @NotNull(message = "商品SkuID列表不能为空")
    private List<Long> skuIdSubList;

}
