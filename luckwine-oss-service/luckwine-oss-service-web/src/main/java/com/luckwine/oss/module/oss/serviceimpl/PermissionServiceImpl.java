package com.luckwine.oss.module.oss.serviceimpl;

import com.luckwine.oss.module.oss.dao.PermissionDao;
import com.luckwine.oss.module.oss.entity.Permission;
import com.luckwine.oss.module.oss.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限接口实现
 * @author Howell
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionDao getRepository() {
        return permissionDao;
    }

    @Override
    public List<Permission> findByLevelOrderBySortOrder(Integer level) {

        return permissionDao.findByLevelOrderBySortOrder(level);
    }

    @Override
    public List<Permission> findByParentIdOrderBySortOrder(String parentId) {

        return permissionDao.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type) {

        return permissionDao.findByLevelAndTypeOrderBySortOrder(level, type);
    }

    @Override
    public List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, String parentId) {

        return permissionDao.findByTypeAndParentIdOrderBySortOrder(type, parentId);
    }
}