package com.luckwine.trade.integration.service.base;

import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import com.luckwine.trade.integration.validator.ValidationException;
import com.luckwine.trade.integration.validator.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 参数校验
 * Created by Winlone on 2018/9/20.
 */
@Component
public class ParamValidateService {

    @Autowired
    ValidatorUtil validatorUtil;

    public TradeCarrier validate(TradeCarrier tradeCarrier) throws ValidationException {
        //参数校验
        validatorUtil.validateMutil(tradeCarrier);

        //核心订单载体创建
        OrderCarrier orderCarrier = new OrderCarrier();
        tradeCarrier.setOrderCarrier(orderCarrier);

        return tradeCarrier;
    }
}
