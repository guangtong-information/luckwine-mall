package com.luckwine.portal.module.portal.service;



import com.luckwine.portal.module.portal.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 * @author HowellYang
 */
public interface LogService {

    /**
     * 日志搜索
     * @param key
     * @param pageable
     * @return
     */
    Page<Log> searchLog(String key, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
