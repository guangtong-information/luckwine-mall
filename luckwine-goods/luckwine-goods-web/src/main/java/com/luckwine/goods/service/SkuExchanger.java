package com.luckwine.goods.service;

import com.luckwine.goods.model.base.Sku;
import com.luckwine.goods.model.request.spu.SkuRequest;
import com.luckwine.goods.model.request.spu.SkuVO;
import com.luckwine.parent.entitybase.transform.AbstractExchanger;
import com.luckwine.parent.tools.common.ValueUtil;
import com.luckwine.parent.tools.date.DateStyle;
import com.luckwine.parent.tools.date.DateUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;


/**
 * Title: sku表
 * Description: 自定义转换类,VO to DB 或 DB TO VO,MAP to DB等
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018-9-29 10:52:15
 */
@Component("skuExchanger")
public class SkuExchanger extends AbstractExchanger<SkuRequest, Sku> {

	@Override
	public void dbFillVo(Sku db, SkuRequest vo) {
		vo.setId(db.getId());
		vo.setSkuName(db.getSkuName());
		vo.setSpuId(db.getSpuId());
		vo.setProps(db.getProps());
		vo.setPropsStr(db.getPropsStr());
		vo.setQuantity(db.getQuantity());
		vo.setSaleCount(db.getSaleCount());
		vo.setPrice(db.getPrice());
		vo.setStatus(db.getStatus());
		vo.setCreateTime(db.getCreateTime());
		vo.setCreateTimeStr(DateUtil.DateToString(db.getCreateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		vo.setUpdateTime(db.getUpdateTime());
		vo.setUpdateTimeStr(DateUtil.DateToString(db.getUpdateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
	}

	@Override
	public void voFillDb(SkuRequest vo, Sku db) {
		db.setId(vo.getId());
		db.setSkuName(vo.getSkuName());
		db.setSpuId(vo.getSpuId());
		db.setProps(vo.getProps());
		db.setPropsStr(vo.getPropsStr());
		db.setQuantity(vo.getQuantity());
		db.setSaleCount(vo.getSaleCount());
		db.setPrice(vo.getPrice());
		db.setStatus(vo.getStatus());
		if (vo.getCreateTime() != null) {
			db.setCreateTime(vo.getCreateTime());
		} else {
			db.setCreateTime(ValueUtil.toDateNull(vo.getCreateTimeStr()));
		}
		if (vo.getUpdateTime() != null) {
			db.setUpdateTime(vo.getUpdateTime());
		} else {
			db.setUpdateTime(ValueUtil.toDateNull(vo.getUpdateTimeStr()));
		}
	}

	@Override
	public void mapFillVo(Map<String, ?> map, SkuRequest vo) {

		vo.setId(ValueUtil.toLong(map.get("id")));
		vo.setSkuName(ValueUtil.toStr(map.get("skuName")));
		vo.setSpuId(ValueUtil.toStr(map.get("spuId")));
		vo.setProps(ValueUtil.toStr(map.get("props")));
		vo.setPropsStr(ValueUtil.toStr(map.get("propsStr")));
		vo.setQuantity(ValueUtil.toLongNull(map.get("quantity")));
		vo.setSaleCount(ValueUtil.toLongNull(map.get("saleCount")));
		vo.setPrice((BigDecimal) map.get("price"));
		vo.setStatus(ValueUtil.toStr(map.get("status")));
		vo.setCreateTime(ValueUtil.toDateNull(map.get("createTime")));
		vo.setCreateTimeStr(DateUtil.DateToString(vo.getCreateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		vo.setUpdateTime(ValueUtil.toDateNull(map.get("updateTime")));
		vo.setUpdateTimeStr(DateUtil.DateToString(vo.getUpdateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
	}

	@Override
	public void mapFillDb(Map<String, ?> map, Sku db) {

		db.setId(ValueUtil.toLongNull(map.get("id")));
		db.setSkuName(ValueUtil.toStr(map.get("skuName")));
		db.setSpuId(ValueUtil.toStr(map.get("spuId")));
		db.setProps(ValueUtil.toStr(map.get("props")));
		db.setPropsStr(ValueUtil.toStr(map.get("propsStr")));
		db.setQuantity(ValueUtil.toLongNull(map.get("quantity")));
		db.setSaleCount(ValueUtil.toLongNull(map.get("saleCount")));
		db.setPrice((BigDecimal) map.get("price"));
		db.setStatus(ValueUtil.toStr(map.get("status")));
		db.setCreateTime(ValueUtil.toDateNull(map.get("createTime")));
		db.setUpdateTime(ValueUtil.toDateNull(map.get("updateTime")));
	}

	@Override
	public SkuRequest newInstanceV() {
		return new SkuRequest();
	}

	@Override
	public Sku newInstanceD() {
		return new Sku();
	}

}
