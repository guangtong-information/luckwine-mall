package com.luckwine.trade;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.trade.dao"})
@SpringBootApplication(scanBasePackages = {"com.luckwine.trade"})
@ImportResource({"classpath:spring-integration.xml", "classpath:elastic-job.xml"})
@EnableDubbo
public class TradeApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TradeApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
