package com.luckwine.oss.module.oss.dao;


import com.luckwine.oss.base.OSSBaseDao;
import com.luckwine.oss.module.oss.entity.User;

import java.util.List;

/**
 * 用户数据处理层
 * @author HowellYang
 */
public interface UserDao extends OSSBaseDao<User,String> {

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

    /**
     * 通过状态和类型获取用户
     * @param status
     * @param type
     * @return
     */
    List<User> findByStatusAndType(Integer status, Integer type);
}
