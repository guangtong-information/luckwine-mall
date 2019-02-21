package com.luckwine.bgw.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "express_goods")
public class ExpressGoods extends BaseRequest {

    @Id
    @NotBlank(message = "商品单号不能为空")
    private String goodsOrderCode;

    @NotBlank(message = "商品名称不能为空")
    private String goodsName;

    //商品件数
    private Integer goodsQuantity;

    //商品重量kg
    private BigDecimal goodsWeight;

    //商品体积m3
    private BigDecimal goodsVolume;

    //商品描述
    private String goodsDesc;

    @NotBlank(message = "商品SKU编码不能为空")
    private String goodsCode;

    //商品价格
    private BigDecimal goodsPrice;

    @NotBlank(message = "子单号不能为空")
    private String subOrderCode;

    @NotBlank(message = "主单号不能为空")
    private String mainOrderCode;

    private Date createDate;

    private Date updateDate;

}