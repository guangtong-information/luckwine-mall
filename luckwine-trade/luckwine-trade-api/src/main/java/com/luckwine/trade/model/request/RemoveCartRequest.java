package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 *
 * 删除购物车项
 */
@Data
@ToString(callSuper = true)
public class RemoveCartRequest extends BaseRequest {

    @NotNull(message = "用户登录账号不能为空")
    private String loginAccount;

    @NotBlank(message = "删除购物车id不能为空")
    @Size(min = 1,message = "删除购物车商品数量不能小于1")
    private List<String> cardIds;
}
