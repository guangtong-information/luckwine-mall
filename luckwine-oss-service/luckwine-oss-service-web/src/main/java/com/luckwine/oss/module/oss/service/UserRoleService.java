package com.luckwine.oss.module.oss.service;


import com.luckwine.oss.base.OSSBaseService;
import com.luckwine.oss.module.oss.entity.UserRole;

import java.util.List;

/**
 * 用户角色接口
 * @author HowellYang
 */
public interface UserRoleService extends OSSBaseService<UserRole,String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
