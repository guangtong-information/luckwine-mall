package com.luckwine.customer.model.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CustomerServiceResp  implements Serializable {
    private static final long serialVersionUID = 2082291271888979939L;
    String appName;
}
