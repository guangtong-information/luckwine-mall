package com.luckwine.synthesize.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.enums.AdContentEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class AdContentUpdateJob implements SimpleJob {

    @Autowired
    private AdContentMapper adContentMapper;


    @Override
    public void execute(ShardingContext shardingContext) {
        String supplierCode = shardingContext.getShardingParameter();
        log.info("更新广告状态:{}", supplierCode);

        //查询待生效和已生效的广告（状态不为已下架的广告）

        Example example = new Example(AdContent.class);
        example.createCriteria()
                 .andNotEqualTo("status", AdContentEnum.STATUS_2.getCode());
        List<AdContent> adContents = adContentMapper.selectByExample(example);
        log.info("查询到待生效和已生效的广告数:{}",adContents.size());

        Date curDate = new Date();

        for (AdContent adContent : adContents) {
            //如果在生效时间内，则将未生效的置为生效，如果已过生效时间，则将已生效的的设置未已下架
            if(adContent.getStatus() == Integer.parseInt(AdContentEnum.STATUS_0.getCode())
                    && curDate.after(adContent.getTimeStart())
                    && curDate.before(adContent.getTimeEnd())){
                log.info("{}广告，现将状态更改为已生效", adContent);
                adContent.setStatus(Integer.parseInt(AdContentEnum.STATUS_1.getCode()));
                adContentMapper.updateByPrimaryKeySelective(adContent);
            }else if(adContent.getStatus() == Integer.parseInt(AdContentEnum.STATUS_1.getCode())
                    && curDate.after(adContent.getTimeEnd())){
                log.info("{}广告，现将状态更改为已下架", adContent);
                adContent.setStatus(Integer.parseInt(AdContentEnum.STATUS_2.getCode()));
                adContentMapper.updateByPrimaryKeySelective(adContent);
            }else{
                log.info("{}广告无需更新", adContent);
            }
        }
    }
}
