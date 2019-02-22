package com.luckwine.portal.config.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis缓存
 */
@Slf4j
@Component
public class RedisCacheDao {

    public static final String APP_NAME = "portal";

    @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Autowired
    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private JedisSentinelPool jedisSentinelPool;

    @Autowired
    private JedisPool jedisPool;

    /**
     * set redis数据
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        Jedis jedis = null;
        try {
            //jedis = jedisSentinelPool.getResource();
            jedis = jedisPool.getResource();
            byte[] serializeCacheKey = stringRedisSerializer.serialize(APP_NAME + "_" + key);
            byte[] serializeValue = jackson2JsonRedisSerializer.serialize(value);
            jedis.set(serializeCacheKey, serializeValue);
        } catch (Exception e) {
            log.error("Redis缓存数据错误\n" + e.toString());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * set redis数据
     *
     * @param key
     * @param expireTime
     * @param value
     */
    public void setEx(String key, int expireTime, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] serializeCacheKey = stringRedisSerializer.serialize(APP_NAME + "_" + key);
            byte[] serializeValue = jackson2JsonRedisSerializer.serialize(value);
            jedis.setex(serializeCacheKey, expireTime, serializeValue);
        } catch (Exception e) {
            log.error("Redis缓存数据错误\n" + e.toString());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * get redis数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        try {
            //jedis = jedisSentinelPool.getResource();
            //byte[] serializeCacheKey = stringRedisSerializer.serialize(APP_NAME + "_" + key);
            return redisTemplate.opsForValue().get(APP_NAME + "_" + key);
        } catch (Exception e) {
            log.error("Redis缓存数据错误\n" + e.toString());
        }
        return null;
    }

    /**
     * get redis数据
     *
     * @param key
     * @return
     */
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] serializeCacheKey = stringRedisSerializer.serialize(APP_NAME + "_" + key);
            jedis.del(serializeCacheKey);
        } catch (Exception e) {
            log.error("Redis缓存数据错误\n" + e.toString());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
