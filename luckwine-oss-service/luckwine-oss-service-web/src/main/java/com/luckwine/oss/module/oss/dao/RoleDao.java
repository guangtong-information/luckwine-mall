package com.luckwine.oss.module.oss.dao;

import com.luckwine.oss.base.OSSBaseDao;
import com.luckwine.oss.module.oss.entity.Role;

import java.util.List;

/**
 * 角色数据处理层
 * @author HowellYang
 */
public interface RoleDao extends OSSBaseDao<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
