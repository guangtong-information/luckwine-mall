package com.luckwine.bgw.service;

import com.luckwine.bgw.BgwApplication;
import com.luckwine.bgw.model.base.ExpressGoods;
import com.luckwine.bgw.model.base.ExpressUser;
import com.luckwine.bgw.model.enums.PayType;
import com.luckwine.bgw.model.enums.ShipperCode;
import com.luckwine.bgw.model.request.EOrderCreateReq;
import com.luckwine.bgw.model.request.EOrderRemoveReq;
import com.luckwine.bgw.model.request.EOrderTracesGetReq;
import com.luckwine.bgw.model.response.EOrderCreateRes;
import com.luckwine.bgw.model.response.EOrderRemoveRes;
import com.luckwine.bgw.model.response.EOrderTracesGetRes;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BgwApplication.class)
@WebAppConfiguration
public class EOrderServiceTest {

    @Resource
    private EOrderServiceImpl expressOrderService;

    /**
     * 创建电子面单
     * @throws Exception
     */
    @Test
    public void testCreateEOrder() throws Exception{

        EOrderCreateReq eOrderCreateReq = new EOrderCreateReq();
        // 不可以重复
        eOrderCreateReq.setSubOrderCode("20181118002");
        eOrderCreateReq.setShipperCode(ShipperCode.SF.getCode());
        eOrderCreateReq.setPayType(PayType.CashCollect.getCode());
        eOrderCreateReq.setExpType("1");
        //注意，测试的时候，统一改成1，不通知快递员
        eOrderCreateReq.setIsNotice("1");
        eOrderCreateReq.setIsReturnPrintTemplate("0");

        ExpressUser sender = new ExpressUser();
        sender.setName("钟先生");
        sender.setMobile("13533659547");
        sender.setProvinceName("广东省");
        sender.setCityName("广州市");
        sender.setExpAreaName("海珠区");
        sender.setAddress("海珠电大校内");
        eOrderCreateReq.setSender(sender);

        ExpressUser receiver = new ExpressUser();
        receiver.setName("钟先生");
        receiver.setMobile("13533659547");
        receiver.setProvinceName("广东省");
        receiver.setCityName("广州市");
        receiver.setExpAreaName("天河区");
        receiver.setAddress("沙河大街东二巷31号");
        eOrderCreateReq.setReceiver(receiver);

        List<ExpressGoods> expressGoodsList = new ArrayList<ExpressGoods>();
        ExpressGoods expressGoods = new ExpressGoods();
        expressGoods.setGoodsName("书");
        expressGoodsList.add(expressGoods);
        eOrderCreateReq.setExpressGoodsList(expressGoodsList);
        eOrderCreateReq.setQuantity(1);

        CommonRequest<EOrderCreateReq> request = new CommonRequest<>();
        request.setRequest(eOrderCreateReq);
        CommonResponse<EOrderCreateRes> commonResponse = expressOrderService.createEOrder(request);

        System.out.println(commonResponse);

    }

    /**
     * 物流轨迹查询（即时查询接口）
     */
    @Test
    public void testGetEOrderTraces(){

        EOrderTracesGetReq eOrderTracesGetReq = new EOrderTracesGetReq();
        eOrderTracesGetReq.setLogisticCode("251461834243");
        eOrderTracesGetReq.setShipperCode(ShipperCode.SF.getCode());

        CommonRequest<EOrderTracesGetReq> commonRequest = new CommonRequest<>();
        commonRequest.setRequest(eOrderTracesGetReq);

        CommonResponse<EOrderTracesGetRes> commonResponse = expressOrderService.getEOrderTraces(commonRequest);
        System.out.println(commonResponse);
    }

    /**
     * 取消电子面单
     */
    @Test
    public void testRemoveEOrder(){

        EOrderRemoveReq eOrderRemoveReq = new EOrderRemoveReq();
        eOrderRemoveReq.setOrderCode("20181118002");
        eOrderRemoveReq.setLogisticCode("251499052530");

        eOrderRemoveReq.setShipperCode(ShipperCode.SF.getCode());

        CommonRequest<EOrderRemoveReq> commonRequest = new CommonRequest<>();
        commonRequest.setRequest(eOrderRemoveReq);

        CommonResponse<EOrderRemoveRes> commonResponse = expressOrderService.removeEOrder(commonRequest);
        System.out.println(commonResponse);
    }

}
