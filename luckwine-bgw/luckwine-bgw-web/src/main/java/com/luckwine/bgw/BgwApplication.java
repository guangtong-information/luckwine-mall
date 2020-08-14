package com.luckwine.bgw;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.bgw.dao"})
@SpringBootApplication(scanBasePackages = {"com.luckwine.bgw"})
@EnableDubbo
public class BgwApplication{
    public static void main(String[] args) {
        new SpringApplicationBuilder(BgwApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
