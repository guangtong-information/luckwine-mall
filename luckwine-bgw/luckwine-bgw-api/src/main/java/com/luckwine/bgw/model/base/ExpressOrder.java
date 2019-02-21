package com.luckwine.bgw.model.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "express_order")
public class ExpressOrder extends BaseRequest {

    @Id
    @NotBlank(message = "子单号不能为空")
    @JSONField(name = "OrderCode")
    private String subOrderCode;

    @NotBlank(message = "主单号不能为空")
    private String mainOrderCode;

    //会员ID
    private String memberId;

    //商家自定义区域
    private String customArea;

    //发货仓编码(ShipperCode为JDKY 时必填)
    private String wareHouseId;

    //运输方式:1-陆运，2-空运，不填默认为1
    private String transType;

    //是否要求签回单,0-不要求，1-要求
    private String isReturnSignBill;

    //签回单操作要求(如：签名、盖章、身份证复印件等)
    private String operateRequire;

    //是否通知快递员上门揽件 0-通知，1-不通知，不填则默认为 1
    private String isNotice;

    //上门揽件开始时间(YYYY-MM-DD HH24:MM:SS)
    private Date startDate;

    //上门揽件结束时间(YYYY-MM-DD HH24:MM:SS)
    private Date endDate;

    @NotBlank(message = "快递公司编码不能为空")
    private String shipperCode;

    @NotBlank(message = "运费支付方式（1现付、2到付、3月结、4第三方-仅SF支持）不能为空")
    private String payType;

    @NotBlank(message = "快递类型（快递鸟接口参数）不能为空")
    private String expType;

    //快递运费
    private BigDecimal cost;

    //其他费用
    private BigDecimal otherCost;

    //商品总重量kg
    private BigDecimal weight;

    @NotNull(message = "商品包裹总数不能为空")
    private int quantity;

    //包裹总体积m3
    private BigDecimal volume;

    //备注
    private String remark;

    //是否返回电子面单模板（0-不需要，1-需要）
    private String isReturnPrintTemplate;

    //快递单号(物流公司返回的订单号)
    private String logisticCode;

    //快递鸟平台返回的订单号
    private String kdnOrderCode;

    //物流状态：0-无轨迹 1-已揽收 2-在途中 3-签收 4-问题件
    private String state;

    // 物流轨迹，存放JSON字符串
    private String traces;

    //返回结果描述
    private String resultMsg;

    //返回编码
    private String resultCode;

    private Date createDate;

    private Date updateDate;

}