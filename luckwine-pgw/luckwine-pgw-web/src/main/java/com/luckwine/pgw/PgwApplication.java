package com.luckwine.pgw;

import com.luckwine.pgw.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.pgw.dao"})
@SpringBootApplication(scanBasePackages = {"com.luckwine.pgw"})
@EnableDubbo
@EnableCaching
@EnableConfigurationProperties({AlipayConfig.class})
@ImportResource("classpath:elastic-job.xml")
@Slf4j
public class PgwApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PgwApplication.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
