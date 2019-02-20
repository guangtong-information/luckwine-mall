package com.luckwine.acct.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 账户冻结类
 */
@Data
@ToString(callSuper = true)
public class AcctFreezeBatchRequest extends BaseRequest {

    /**
     * 余额账户号列表
     */
    @Size(min = 1,message = "操作的账户号不能小于1")
    private List<String> acctCode;

}

