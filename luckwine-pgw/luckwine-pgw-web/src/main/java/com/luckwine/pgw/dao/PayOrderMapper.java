package com.luckwine.pgw.dao;

import com.luckwine.pgw.model.base.PayOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PayOrderMapper extends Mapper<PayOrder> {
    List<String> selectPayOrders(@Param("date") String date);
}
