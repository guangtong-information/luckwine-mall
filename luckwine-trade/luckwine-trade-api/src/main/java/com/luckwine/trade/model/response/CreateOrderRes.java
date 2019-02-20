package com.luckwine.trade.model.response;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 交易订单返回信息
 * Created by Winlone on  2018/9/20.
 */
@Data
@ToString(callSuper = true)
public class CreateOrderRes implements java.io.Serializable {

	private static final long serialVersionUID = -2099587684997705741L;

	/**
	 * 主单号
	 */
	private String mainOrderCode;

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
}