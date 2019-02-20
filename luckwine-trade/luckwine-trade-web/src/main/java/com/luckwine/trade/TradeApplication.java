package com.luckwine.trade;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.luckwine.trade.dao"})
@SpringBootApplication(scanBasePackages = {"com.luckwine.trade"})
@ImportResource({"classpath:spring-integration.xml","classpath:elastic-job.xml"})
@EnableDubbo
public class TradeApplication extends SpringBootServletInitializer {

    /**
     * 外部容器启动的时候，需要重写此方法
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TradeApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }

}
