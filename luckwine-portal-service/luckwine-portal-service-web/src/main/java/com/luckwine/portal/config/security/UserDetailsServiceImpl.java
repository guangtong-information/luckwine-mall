package com.luckwine.portal.config.security;

import cn.hutool.core.util.StrUtil;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.portal.customer.service.CustomerInfoService;
import com.luckwine.portal.exception.LoginFailLimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author HowellYang
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Value("${portal.loginAfterTime}")
    private Integer loginAfterTime;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CustomerInfoService CustomerInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String flagKey = "loginFailFlag:"+username;
        String value = redisTemplate.opsForValue().get(flagKey);
        if(StrUtil.isNotBlank(value)){
            //超过限制次数
            throw new LoginFailLimitException("登录错误次数超过限制，请"+loginAfterTime+"分钟后再试");
        }
        CustInfo user = CustomerInfoService.findByLoginAccount(username);
        return new SecurityUserDetails(user);
    }
}
