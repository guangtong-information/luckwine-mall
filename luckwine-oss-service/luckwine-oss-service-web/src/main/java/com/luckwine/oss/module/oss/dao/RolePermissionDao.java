package com.luckwine.oss.module.oss.dao;

import com.luckwine.oss.base.OSSBaseDao;
import com.luckwine.oss.module.oss.entity.RolePermission;

import java.util.List;

/**
 * 角色权限数据处理层
 * @author Howell
 */
public interface RolePermissionDao extends OSSBaseDao<RolePermission,String> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}