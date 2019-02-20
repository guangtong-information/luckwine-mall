package com.luckwine.goods.dao;

import com.luckwine.goods.model.base.Sku;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SkuMapper extends Mapper<Sku>, InsertListMapper<Sku> {

	int updateDeductionStockById(Sku sku);

	int updateAddStockById(Sku sku);
}
