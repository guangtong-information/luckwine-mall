package com.luckwine.oss.module.oss.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luckwine.oss.module.oss.entity.Role;
import com.luckwine.oss.module.oss.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HowellYang
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}
