package com.luckwine.trade.model.request.base;


import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 交易基础请求
 * Created by winlone on 2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class TransBaseRequest extends BaseRequest {
    /**
     * 购买用户登录账号
     * */
    @NotBlank(message = "购买用户的登录账号不能为空")
    private String buyLoginAccount;

    /**
     * 交易备注
     * */
    private String note;

}
