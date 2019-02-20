package com.luckwine.pgw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    private String appId;

    private String userAccount;

    private String serverUrl;

    private String privateKey;

    private String publicKey;

    private String charset;

    private String format;

    private String signType;

    private String returnUrl;

    private String notifyUrl;

    private String timeoutExpress;

    private String billSuffix;

    private String billSavePath;


}
