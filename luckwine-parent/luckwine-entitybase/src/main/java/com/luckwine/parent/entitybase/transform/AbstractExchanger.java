package com.luckwine.parent.entitybase.transform;

import java.util.Map;

/**
 * 数据交换类，目前为实体类与请求类的转换
 * Title:
 * Description:
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018年09月29日下午3:10:45
 */
public abstract class AbstractExchanger<V, D> {
	public abstract V newInstanceV();

	public abstract D newInstanceD();

	public abstract void dbFillVo(D db, V vo);

	public abstract void voFillDb(V vo, D db);

	public abstract void mapFillVo(Map<String, ?> map, V vo);

	public abstract void mapFillDb(Map<String, ?> map, D db);

	public V dbToVo(D db) {
		V vo = newInstanceV();
		dbFillVo(db, vo);
		return vo;
	}

	public D voToDb(V vo) {
		D db = newInstanceD();
		voFillDb(vo, db);
		return db;
	}

	public V mapToVo(Map<String, ?> map) {
		V vo = newInstanceV();
		mapFillVo(map, vo);
		return vo;
	}

	public D mapToDb(Map<String, ?> map) {
		D db = newInstanceD();
		mapFillDb(map, db);
		return db;
	}
}
