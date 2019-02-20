package com.luckwine.trade.model.response;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 交易订单返回信息
 * Created by Winlone on  2018/11/7.
 */
@Data
@ToString(callSuper = true)
public class PayOrderRes implements java.io.Serializable {

	private static final long serialVersionUID = -2099587684997705741L;

	/**
	 * 主单号
	 */
	private String mainOrderCode;

	/**
	 * 订单资金流水
	 */
	private String capSeq;

	/**
	 * 资金模块来源:ACCT、MARKETING、PGW
	 */
	private String capModule;

	/**
	 * 资金系统类型：BALANCE、ALLINPAY、ALIPAY、COUPON
	 */
	private String capSysType;

	/**
	 * 交易总金额
	 */
	private BigDecimal totalAmount;

	/**
	 * 实付金额
	 */
	private BigDecimal payAmount;

	/**
	 * 优惠金额
	 */
	private BigDecimal discAmount;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 支付数据
	 * 1. 支付宝二维码：二维码支付链接，需要前端根据链接生成二维码，返回值如：https://qr.alipay.com/bax06276vdeevnkqhfpf0008
	 * 2. 支付宝PC网页：返回html格式字符串，前端直接唤起支付宝PC版收银台
	 */
	private String body;
}