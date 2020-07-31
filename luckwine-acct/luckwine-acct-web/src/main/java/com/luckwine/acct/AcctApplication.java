package com.luckwine.acct;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.acct.dao"})
@SpringBootApplication(scanBasePackages = {"com.luckwine.acct"})
@EnableDubbo
public class AcctApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AcctApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
