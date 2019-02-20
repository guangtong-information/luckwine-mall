package com.luckwine.oss.module.oss.service;



import com.luckwine.oss.base.OSSBaseService;
import com.luckwine.oss.module.oss.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 * @author HowellYang
 */
public interface LogService extends OSSBaseService<Log,String> {

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
