package com.luckwine.pgw.dao;

import com.luckwine.pgw.model.base.PayOrderDiff;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface PayOrderDiffMapper extends Mapper<PayOrderDiff>, InsertListMapper<PayOrderDiff> {
}
