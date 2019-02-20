package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 *  查询购物车
 */
@Data
@ToString(callSuper = true)
public class QueryCartRequest extends BaseRequest {

    @NotNull(message = "用户登录账号不能为空")
    private String loginAccount;


}
