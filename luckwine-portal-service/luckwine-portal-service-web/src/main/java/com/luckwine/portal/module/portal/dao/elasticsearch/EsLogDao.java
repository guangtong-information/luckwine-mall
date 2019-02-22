package com.luckwine.portal.module.portal.dao.elasticsearch;

import com.luckwine.portal.module.portal.entity.elasticsearch.EsLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author HowellYang
 */
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {
}
