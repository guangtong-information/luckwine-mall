package com.luckwine.oss.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.luckwine.parent.filter.HttpLoggingFilter;
import com.luckwine.parent.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Bean
//    public ApplicationConfig applicationConfig(){
//        ApplicationConfig config = new ApplicationConfig();
//        config.setQosEnable(false);
//        config.setQosAcceptForeignIp(false);
//        return config;
//    }

    @Bean
    public HttpLoggingFilter httpLoggingFilter(){
        return new HttpLoggingFilter();
    }

    @Bean
    public SpringContextUtil springContextUtil(){
        return new SpringContextUtil();
    }
}
