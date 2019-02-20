package com.luckwine.oss.module.oss.dao;

import com.luckwine.oss.base.OSSBaseDao;
import com.luckwine.oss.module.oss.entity.UserRole;

import java.util.List;

/**
 * 用户角色数据处理层
 * @author HowellYang
 */
public interface UserRoleDao extends OSSBaseDao<UserRole,String> {

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
