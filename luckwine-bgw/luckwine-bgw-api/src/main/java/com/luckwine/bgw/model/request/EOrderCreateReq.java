package com.luckwine.bgw.model.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.luckwine.bgw.model.base.ExpressGoods;
import com.luckwine.bgw.model.base.ExpressOrder;
import com.luckwine.bgw.model.base.ExpressUser;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ToString(callSuper = true)
public class EOrderCreateReq extends ExpressOrder {

    @NotNull(message = "发件人信息不能为空")
    private ExpressUser sender;

    @NotNull(message = "收件人信息不能为空")
    private ExpressUser receiver;

    @Size(min = 1,message = "商品数量不能少于1")
    @JSONField(name = "Commodity")
    private List<ExpressGoods> expressGoodsList;

}
