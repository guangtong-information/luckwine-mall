package com.luckwine.customer.model.request;


import com.luckwine.customer.model.base.CustInfo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CustomerServiceReq extends CustInfo implements Serializable {
    private static final long serialVersionUID = -7651894767644042906L;

    private String createStartDate;

    private String createEndDate;
}
