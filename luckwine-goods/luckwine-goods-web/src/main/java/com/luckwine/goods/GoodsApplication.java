package com.luckwine.goods;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.util.Properties;

@MapperScan(basePackages = {"com.luckwine.goods.dao"})
@SpringBootApplication(scanBasePackages = {"com.luckwine.goods"})
@EnableDubbo
@EnableCaching
@Slf4j
public class GoodsApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GoodsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //获取es, redis 信息
        try {
            MainResponse mainResponse = restHighLevelClient.info(RequestOptions.DEFAULT);
            log.info("es : cluster name:{}", mainResponse.getClusterName());
            log.info("es : version:{}", mainResponse.getVersion());

            Properties redisProperties = stringRedisTemplate.execute(new RedisCallback<Properties>() {
                @Override
                public Properties doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    return redisConnection.serverCommands().info();
                }
            });
            log.info("redis: {}", redisProperties);
        } catch (IOException e) {
            log.error("存在中间件异常", e);
        }
    }
}
