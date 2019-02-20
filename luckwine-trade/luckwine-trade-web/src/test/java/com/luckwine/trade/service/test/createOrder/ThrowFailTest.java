package com.luckwine.trade.service.test.createOrder;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.trade.dao.TradeMainOrderMapper;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.service.test.base.BaseTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

/**
 * 插入数据库异常测试
 */
public class ThrowFailTest extends BaseTest {

    @Spy
    private TradeMainOrderMapper tradeMainOrderMapper;

    /**
     * 下单服务：插入数据库异常
     */
    @Test
    public void testDataBaseFail() {
//        TradeMainOrder record = new TradeMainOrder();
//        try {
//            tradeMainOrderMapper.insertSelective(record);
//        } catch (Exception e) {
//            throw e;
//        }

    }

    /**
     * 通用请求参数
     */
    @Override
    public void initRequestInner() {

    }

    /**
     * mock测试
     */
    @Override
    public void fullMockInner() {
        //数据库异常
        Mockito.doThrow(new CommonException(ResponseCodeConstant.DB_EXCEPTION.getResponseCode(), ResponseCodeConstant.DB_EXCEPTION.getResponseDesc())).when(tradeMainOrderMapper).insertSelective(Mockito.any(TradeMainOrder.class));
    }
}
