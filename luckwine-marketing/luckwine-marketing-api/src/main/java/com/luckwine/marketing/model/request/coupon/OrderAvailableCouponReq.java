package com.luckwine.marketing.model.request.coupon;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
public class OrderAvailableCouponReq extends BaseRequest {

    @NotNull(message = "登陆账号不能为空")
    private String loginAccount;

    @NotNull(message = "商品SkuID列表不能为空")
    private List<Long> skuIdList;

}
