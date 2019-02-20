package com.luckwine.customer.model.request;

import com.luckwine.customer.model.base.CustInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustInfoUpdateReq implements Serializable {

//	客户信息对象
	private CustInfo custInfo;

//	更新字段
	private List<String> columns;

}
