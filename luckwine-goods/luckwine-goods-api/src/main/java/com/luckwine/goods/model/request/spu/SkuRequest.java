package com.luckwine.goods.model.request.spu;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: sku表
 * Description: MC工具生成
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018-9-29 10:52:15
 */
@Data
public class SkuRequest extends BaseRequest {
	// 主键
	private Long id;
	// sku名称
	private String skuName;
	// spu id
	private String spuId;
	// Sku属性串
	private String props;
	// 描述
	private String propsStr;
	// 库存数
	private Long quantity;
	// 销售数量
	private Long saleCount;
	// 销售价格
	private BigDecimal price;
	// 状态
	private String status;
	// 创建时间
	private Date createTime;
	// 创建时间字符
	private String createTimeStr;
	// 创建时间开始
	private String createTimeBeginStr;
	// 创建时间结束
	private String createTimeEndStr;
	// 更新时间
	private Date updateTime;
	// 更新时间字符
	private String updateTimeStr;
	// 更新时间开始
	private String updateTimeBeginStr;
	// 更新时间结束
	private String updateTimeEndStr;

}