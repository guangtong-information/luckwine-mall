package com.luckwine.oss.module.oss.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luckwine.oss.module.oss.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HowellYang
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Permission> findByUserId(@Param("userId") String userId);

    /**
     * 通过roleId获取
     * @param roleId
     * @return
     */
    List<Permission> findByRoleId(@Param("roleId") String roleId);
}
