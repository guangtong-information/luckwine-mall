package com.luckwine.synthesize.dao;

import com.luckwine.synthesize.model.base.AdContent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdContentMapper extends Mapper<AdContent> {

    List<AdContent> selectAdContentByPageId(@Param("pageId") String pageId, @Param("status") int status );
}