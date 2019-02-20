package com.luckwine.parent.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Log
public class SpringContextUtil implements ApplicationContextAware {
    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     * @param className
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> className) throws BeansException {
        try {
            if(applicationContext != null){
                return applicationContext.getBean(className);
            }
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }

    /**
     * get dubbo app name
     * @return
     */
    public static String getApplicationName(){
        ApplicationConfig applicationConfig = SpringContextUtil.getBean(ApplicationConfig.class);
        if(applicationConfig != null && applicationConfig.getName() != null){
            return applicationConfig.getName();
        }
        return null;
    }
}
