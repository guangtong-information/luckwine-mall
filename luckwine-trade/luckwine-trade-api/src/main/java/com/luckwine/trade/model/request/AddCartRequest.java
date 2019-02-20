package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 *
 * 添加购物车
 */
@Data
@ToString(callSuper = true)
public class AddCartRequest extends BaseRequest {

    @NotBlank(message = "用户登录账号不能为空")
    private String loginAccount;

    @NotNull(message = "加入购物车的规格id不能为空")
    private Long skuId;

    @Min(value = 1,message = "商品数量不能小于1")
    @NotNull(message = "商品数量不能为空")
    private Integer count;

}
