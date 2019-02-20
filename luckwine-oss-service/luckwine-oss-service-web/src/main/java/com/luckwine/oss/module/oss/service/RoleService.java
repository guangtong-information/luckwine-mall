package com.luckwine.oss.module.oss.service;


import com.luckwine.oss.base.OSSBaseService;
import com.luckwine.oss.module.oss.entity.Role;

import java.util.List;

/**
 * 角色接口
 * @author HowellYang
 */
public interface RoleService extends OSSBaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
