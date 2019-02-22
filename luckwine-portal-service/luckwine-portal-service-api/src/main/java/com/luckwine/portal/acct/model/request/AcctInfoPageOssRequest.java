package com.luckwine.portal.acct.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 账户列表请求参数
 */
@Data
@ToString(callSuper = true)
public class AcctInfoPageOssRequest extends BaseRequest {


    /**
     * 余额账户号
     */
    @ApiModelProperty(value = "余额账户号")
    private String acctCode;

    /**
     * 余额账户名称
     */
    @ApiModelProperty(value = "余额账户名称")
    private String acctName;

    /**
     * 客户登录账号,关联cust_info表
     */
    @ApiModelProperty(value = "客户登录账号")
    private String loginAccount;

    /**
     * 账户状态
     */
    @ApiModelProperty(value = "账户状态")
    private String stat;

    /**
     * 开户开始时间
     */
    @ApiModelProperty(value = "开户开始时间")
    private String createTimeStart;

    /**
     * 开户结束时间
     */
    @ApiModelProperty(value = "开户结束时间")
    private String createTimeEnd;

    /**
     * 账户能力
     */
    @ApiModelProperty(value = "账户能力")
    private String abilityCode;
}

