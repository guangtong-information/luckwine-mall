package com.luckwine.oss.module.oss.serviceimpl.mybatis;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luckwine.oss.module.oss.dao.mapper.UserRoleMapper;
import com.luckwine.oss.module.oss.entity.Role;
import com.luckwine.oss.module.oss.entity.UserRole;
import com.luckwine.oss.module.oss.service.mybatis.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HowellYang
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }
}
