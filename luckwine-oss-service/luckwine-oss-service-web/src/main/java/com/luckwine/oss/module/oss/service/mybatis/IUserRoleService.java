package com.luckwine.oss.module.oss.service.mybatis;


import com.baomidou.mybatisplus.extension.service.IService;
import com.luckwine.oss.module.oss.entity.Role;
import com.luckwine.oss.module.oss.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author HowellYang
 */
@CacheConfig(cacheNames = "userRole")
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    List<Role> findByUserId(@Param("userId") String userId);
}
