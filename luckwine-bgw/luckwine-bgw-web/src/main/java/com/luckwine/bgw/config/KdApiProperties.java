package com.luckwine.bgw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = KdApiProperties.PREFIX)
public class KdApiProperties {

    public static final String PREFIX = "kdapi";

    /**
     * 用户Id
     */
    private String ebusinessid;

    /**
     * Api访问key
     */
    private String apikey;

    /**
     * 预约取件、预约取件取消
     */
    private String orderurl;

    /**
     * 电子面单、电子面单取消
     */
    private String eorderurl;

    /**
     * 即时查询、智选物流
     */
    private String ebusinessorderurl;

    /**
     * 轨迹订阅
     */
    private String subscribeurl;

}
