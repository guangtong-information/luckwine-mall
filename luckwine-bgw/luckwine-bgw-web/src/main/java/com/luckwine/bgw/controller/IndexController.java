package com.luckwine.bgw.controller;

import com.luckwine.bgw.model.base.ExpressGoods;
import com.luckwine.bgw.model.base.ExpressUser;
import com.luckwine.bgw.model.enums.PayType;
import com.luckwine.bgw.model.enums.ShipperCode;
import com.luckwine.bgw.model.request.EOrderCreateReq;
import com.luckwine.bgw.model.response.EOrderCreateRes;
import com.luckwine.bgw.service.EOrderServiceImpl;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class IndexController {

    @Resource
    private EOrderServiceImpl expressOrderService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/actuator/info");
    }

    @RequestMapping(value = "/createEOrder", method = RequestMethod.GET)
    public void createEOrder(HttpServletResponse response) throws IOException {

        EOrderCreateReq eOrderCreateReq = new EOrderCreateReq();
        eOrderCreateReq.setSubOrderCode("20181118003");
        eOrderCreateReq.setShipperCode(ShipperCode.SF.getCode());
        eOrderCreateReq.setPayType(PayType.CashCollect.getCode());
        eOrderCreateReq.setExpType("1");
        eOrderCreateReq.setIsNotice("1");
        eOrderCreateReq.setIsReturnPrintTemplate("1");

        ExpressUser sender = new ExpressUser();
        sender.setName("殷先生");
        sender.setMobile("13326465600");
        sender.setProvinceName("广东省");
        sender.setCityName("广州市");
        sender.setExpAreaName("黄埔区");
        sender.setAddress("科学大道233号知商谷");
        eOrderCreateReq.setSender(sender);

        ExpressUser receiver = new ExpressUser();
        receiver.setName("殷先生");
        receiver.setMobile("13326465600");
        receiver.setProvinceName("广东省");
        receiver.setCityName("广州市");
        receiver.setExpAreaName("海珠区");
        receiver.setAddress("罗马家园1号门");
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

        response.getWriter().println(commonResponse.getResponse().getPrintTemplate());
    }
}
