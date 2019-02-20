package com.luckwine.acct.model.response;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 交易返回信息
 *
 */
@Data
@ToString(callSuper = true)
public class TransRes implements java.io.Serializable {

	private static final long serialVersionUID = -2099587684997705741L;

	/**
	 * 账户请求流水号（订单模块传入）
	 */
	private String requestSeq;
	/**
	 * 客户登录账号,关联cust_info表
	 */
	private String loginAccount;
	/**
	 * 发起账户编号
	 */
	private String acctCode;
	/**
	 * 发起账户名称
	 */
	private String acctName;
	/**
	 * 账户类型,关联账户类型表
	 */
	private String acctTypeCode;

	/**
	 * 可用金额
	 */
	private BigDecimal availBal;

	/**
	 * 账户流水号（账户订单号）
	 */
	private String acctOrderno;

	/**
	 * 外部系统订单号(订单模块的主单号)
	 */
	private String extTrsSeq;

}