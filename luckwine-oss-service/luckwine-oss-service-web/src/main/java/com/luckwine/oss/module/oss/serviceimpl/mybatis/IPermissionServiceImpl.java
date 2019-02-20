package com.luckwine.oss.module.oss.serviceimpl.mybatis;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luckwine.oss.module.oss.dao.mapper.PermissionMapper;
import com.luckwine.oss.module.oss.entity.Permission;
import com.luckwine.oss.module.oss.service.mybatis.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HowellYang
 */
@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper; ;

    @Override
    public List<Permission> findByUserId(String userId) {

        return permissionMapper.findByUserId(userId);
    }

    @Override
    public List<Permission> findByRoleId(String roleId) {

        return permissionMapper.findByRoleId(roleId);
    }
}
