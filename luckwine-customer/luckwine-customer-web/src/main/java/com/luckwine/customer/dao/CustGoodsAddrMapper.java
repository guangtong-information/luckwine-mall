package com.luckwine.customer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luckwine.customer.model.base.CustGoodsAddr;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName CustGoodsAddrMapper
 * @Description 客户收货地址DAO
 * @Author yewenqing 叶文清
 * @Date 2018/10/3 11:19
 * @Version 1.0
 **/
public interface CustGoodsAddrMapper extends BaseMapper<CustGoodsAddr> {

    public boolean updateCustGoodsAddr(@Param("custGoodsAddr") CustGoodsAddr custGoodsAddr);
}
