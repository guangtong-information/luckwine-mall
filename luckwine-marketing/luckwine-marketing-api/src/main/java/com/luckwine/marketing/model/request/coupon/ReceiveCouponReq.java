package com.luckwine.marketing.model.request.coupon;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class ReceiveCouponReq extends BaseRequest {

    @NotNull(message = "登陆账号不能为空")
    private String loginAccount;

//    @NotNull(message = "营销方案ID不能为空")
    private String schemeId;

    @NotNull(message = "优惠券ID不能为空")
    private String couponId ;

}
