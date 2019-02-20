package com.luckwine.trade.integration.carrier;


import com.luckwine.parent.entitybase.request.CommonRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 基础工作流载体
 * Created by Winlone on 2018/9/20.
 */
@Data
@ToString
public class BaseCarrier<Request extends CommonRequest, Result> {

    /**
     * 基础请求.
     */
    @NotNull
    @Valid
    private Request request;

    /**
     * 响应结果.
     */
    private Result result;

    /**
     * 请求核心渠道.
     */
    @NotBlank
    private String integrationChannel;


    /**
     * 结果编码.
     */
    private String resultCode;

    /**
     * 结果描述.
     */
    private String resultDesc;

}
