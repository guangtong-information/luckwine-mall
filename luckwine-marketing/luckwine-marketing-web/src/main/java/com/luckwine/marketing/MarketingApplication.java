package com.luckwine.marketing;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.marketing.dao"})
@SpringBootApplication
@EnableDubbo
@ImportResource("classpath:elastic-job.xml")
public class MarketingApplication{
    public static void main(String[] args) {
        new SpringApplicationBuilder(MarketingApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
