package com.luckwine.synthesize;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.synthesize.dao"})
@SpringBootApplication
@EnableDubbo
@ImportResource("classpath:elastic-job.xml")
public class SynthesizeApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SynthesizeApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
