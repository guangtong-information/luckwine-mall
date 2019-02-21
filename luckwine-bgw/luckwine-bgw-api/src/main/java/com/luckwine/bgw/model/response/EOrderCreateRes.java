package com.luckwine.bgw.model.response;

import com.luckwine.bgw.model.base.ExpressOrder;
import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class EOrderCreateRes extends BaseResponse {

    private ExpressOrder order;

    private String success;

    private String reason;

    private String resultCode;

    private String printTemplate;

}
