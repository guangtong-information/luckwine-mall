package com.luckwine.goods.service;

import com.luckwine.goods.model.base.Spu;
import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.parent.entitybase.transform.AbstractExchanger;
import com.luckwine.parent.tools.common.ValueUtil;
import com.luckwine.parent.tools.date.DateStyle;
import com.luckwine.parent.tools.date.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Title: spu表
 * Description: 自定义转换类,VO to DB 或 DB TO VO,MAP to DB等
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018-9-28 19:29:02
 */
@Component("spuExchanger")
public class SpuExchanger extends AbstractExchanger<SpuSaveRequest, Spu> {

	@Override
	public void dbFillVo(Spu db, SpuSaveRequest vo) {
		vo.setId(db.getId());
		vo.setGoodsName(db.getGoodsName());
		vo.setCategoryId(db.getCategoryId());
		vo.setBrandId(db.getBrandId());
		vo.setTitle(db.getTitle());
		vo.setProps(db.getProps());
		vo.setPropsStr(db.getPropsStr());
		vo.setPicPath(db.getPicPath());
		vo.setDetail(db.getDetail());

		SpuStatus spuStatus = SpuStatus.valueOf(db.getStatus());
		vo.setSpuStatus(spuStatus);

		vo.setCreateTime(db.getCreateTime());
		vo.setCreateTimeStr(DateUtil.DateToString(db.getCreateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		vo.setUpdateTime(db.getUpdateTime());
		vo.setUpdateTimeStr(DateUtil.DateToString(db.getUpdateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
	}

	@Override
	public void voFillDb(SpuSaveRequest vo, Spu db) {
		db.setId(vo.getId());
		db.setGoodsName(vo.getGoodsName());
		db.setCategoryId(vo.getCategoryId());
		db.setBrandId(vo.getBrandId());
		db.setTitle(vo.getTitle());
		db.setProps(vo.getProps());
		db.setPropsStr(vo.getPropsStr());
		db.setPicPath(vo.getPicPath());
		db.setDetail(vo.getDetail());
		db.setStatus(vo.getSpuStatus().name());

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
	public void mapFillVo(Map<String, ?> map, SpuSaveRequest vo) {

		vo.setId(ValueUtil.toStr(map.get("id")));
		vo.setGoodsName(ValueUtil.toStr(map.get("goodsName")));
		vo.setCategoryId(ValueUtil.toLongNull(map.get("categoryId")));
		vo.setBrandId(ValueUtil.toLongNull(map.get("brandId")));
		vo.setTitle(ValueUtil.toStr(map.get("title")));
		vo.setProps(ValueUtil.toStr(map.get("props")));
		vo.setPropsStr(ValueUtil.toStr(map.get("propsStr")));
		vo.setPicPath(ValueUtil.toStr(map.get("picPath")));
		vo.setDetail(ValueUtil.toStr(map.get("detail")));

		SpuStatus spuStatus = SpuStatus.valueOf(ValueUtil.toStr(map.get("status")));
		vo.setSpuStatus(spuStatus);

		vo.setCreateTime(ValueUtil.toDateNull(map.get("createTime")));
		vo.setCreateTimeStr(DateUtil.DateToString(vo.getCreateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
		vo.setUpdateTime(ValueUtil.toDateNull(map.get("updateTime")));
		vo.setUpdateTimeStr(DateUtil.DateToString(vo.getUpdateTime(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
	}

	@Override
	public void mapFillDb(Map<String, ?> map, Spu db) {
		db.setId(ValueUtil.toStr(map.get("id")));
		db.setGoodsName(ValueUtil.toStr(map.get("goodsName")));
		db.setCategoryId(ValueUtil.toLongNull(map.get("categoryId")));
		db.setBrandId(ValueUtil.toLongNull(map.get("brandId")));
		db.setTitle(ValueUtil.toStr(map.get("title")));
		db.setProps(ValueUtil.toStr(map.get("props")));
		db.setPropsStr(ValueUtil.toStr(map.get("propsStr")));
		db.setPicPath(ValueUtil.toStr(map.get("picPath")));
		db.setDetail(ValueUtil.toStr(map.get("detail")));
		db.setStatus(ValueUtil.toStr(map.get("status")));
		db.setCreateTime(ValueUtil.toDateNull(map.get("createTime")));
		db.setUpdateTime(ValueUtil.toDateNull(map.get("updateTime")));
	}

	@Override
	public SpuSaveRequest newInstanceV() {
		return new SpuSaveRequest();
	}

	@Override
	public Spu newInstanceD() {
		return new Spu();
	}

}
