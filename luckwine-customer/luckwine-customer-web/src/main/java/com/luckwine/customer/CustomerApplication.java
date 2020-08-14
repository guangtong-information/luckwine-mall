package com.luckwine.customer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author HowellYang
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.luckwine.customer.dao"})
@EnableDubbo
public class CustomerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomerApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
