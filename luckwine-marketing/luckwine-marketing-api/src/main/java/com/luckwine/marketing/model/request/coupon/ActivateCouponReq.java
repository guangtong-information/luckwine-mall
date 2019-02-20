package com.luckwine.marketing.model.request.coupon;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class ActivateCouponReq extends BaseRequest {

    @NotNull(message = "优惠券号不能为空")
    private String couponNum;

    @NotNull(message = "登录账户不能为空")
    private String loginAccount;

}
