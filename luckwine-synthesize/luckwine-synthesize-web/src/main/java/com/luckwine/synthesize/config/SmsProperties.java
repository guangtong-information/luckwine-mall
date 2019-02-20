package com.luckwine.synthesize.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = SmsProperties.PREFIX)
public class SmsProperties {

    public static final String PREFIX = "sms";

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 地址
     */
    private String cmHost;

    /**
     * 地址
     */
    private String wsHost;

    /**
     * 是否激活真实发送
     */
    private Boolean active;
}
