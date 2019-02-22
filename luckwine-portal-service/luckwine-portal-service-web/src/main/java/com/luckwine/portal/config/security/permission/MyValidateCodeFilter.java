package com.luckwine.portal.config.security.permission;

import com.luckwine.portal.exception.LoginFailLimitException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证码 过滤器
 *
 */

@Component
public class MyValidateCodeFilter  extends OncePerRequestFilter implements InitializingBean {
    /**
     * 登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 创建一个Set 集合 存放 需要验证码的 urls
     */
    private Set<String> urls = new HashSet<>();

    /**
     * spring的一个工具类：用来判断 两字符串 是否匹配
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        //因为登录请求一定要有验证码 ，所以直接 add 到set 集合中
        urls.add("/portal/users/login");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url:urls){
            //如果请求的url 和 配置中的url 相匹配
            if (pathMatcher.match(url,httpServletRequest.getRequestURI())){
                action = true;
            }
        }
        //拦截请求
        if (action){
            logger.info("拦截成功"+httpServletRequest.getRequestURI());
            try {
                validate(new ServletWebRequest(httpServletRequest));
            }catch (LoginFailLimitException exception){
                //返回错误信息给 失败处理器
                failureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse,  exception);
                return;
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        } else {
            //不做任何处理，调用后面的 过滤器
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }


    private void validate(ServletWebRequest request) throws InternalAuthenticationServiceException {
        String captchaId = request.getParameter("captchaId");
        String code = request.getParameter("code");
        String redisCode = redisTemplate.opsForValue().get(captchaId);
        if (StringUtils.isBlank(code)){
            logger.info("验证码不能为空");
            throw new LoginFailLimitException("验证码不能为空");
        }
        if (redisCode == null){
            logger.info("验证码不存在");
            throw new LoginFailLimitException("验证码不存在");
        }
        if (!StringUtils.equals(redisCode.toLowerCase(), code.toLowerCase())){
            logger.info("验证码不匹配"+"codeInSession:"+redisCode +", codeInRequest:"+code);
            throw new LoginFailLimitException("验证码不匹配");
        }
    }

}
