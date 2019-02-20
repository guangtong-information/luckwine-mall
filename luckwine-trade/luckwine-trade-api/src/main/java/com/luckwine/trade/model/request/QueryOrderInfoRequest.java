package com.luckwine.trade.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;


/**
 *
 * 订单查询
 */
@Data
@ToString(callSuper = true)
public class QueryOrderInfoRequest extends BaseRequest {

    /**
     * 主单号
     */
    private String mainOrderCode;


}
