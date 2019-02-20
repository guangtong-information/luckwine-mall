package com.luckwine.acct.model.request;

import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 账户列表响应参数
 */
@Data
@ToString(callSuper = true)
@Table(name = "acct_info")
public class AcctOperRequest extends BaseResponse implements Serializable {

    /**
     * 客户登录账号,关联cust_info表
     */
    @NotNull(message = "关联登录号不能为空")
    private String loginAccount;

    /**
     * 账户名称
     */
    @NotNull(message = "账户名称")
    private String acctName;

    /**
     * 账户类型 关联acct_accttype_dict表
     */
    @NotNull(message = "账户类型不能为空")
    private String acctTypeCode;

    /**
     * 能力编码列表
     */
    private List<String> abilityCodeList;
}
