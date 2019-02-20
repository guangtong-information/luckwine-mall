package com.luckwine.oss.module.oss.dao.elasticsearch;

import com.luckwine.oss.module.oss.entity.elasticsearch.EsLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author HowellYang
 */
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {
}
