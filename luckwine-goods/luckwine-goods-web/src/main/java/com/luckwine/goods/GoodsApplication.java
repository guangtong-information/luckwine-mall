package com.luckwine.goods;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
public class GoodsApplication implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public static void main(String[] args) {
        new SpringApplicationBuilder(GoodsApplication.class).
                web(WebApplicationType.NONE).
                run(args);
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
