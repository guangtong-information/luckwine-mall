package com.luckwine.oss.module.oss.service;

import com.luckwine.oss.base.OSSBaseService;
import com.luckwine.oss.module.oss.entity.Permission;

import java.util.List;

/**
 * 权限接口
 * @author Howell
 */
public interface PermissionService extends OSSBaseService<Permission,String> {

    /**
     * 通过层级查找
     * 默认升序
     * @param level
     * @return
     */
    List<Permission> findByLevelOrderBySortOrder(Integer level);

    /**
     * 通过parendId查找
     * @param parentId
     * @return
     */
    List<Permission> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过类型和层级查找
     * @param level
     * @param type
     * @return
     */
    List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type);

    /**
     * 通过parendId和类型查找
     * @param type
     * @param parentId
     * @return
     */
    List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, String parentId);
}