package com.luckwine.bgw.service;

import com.alibaba.fastjson.JSONObject;
import com.luckwine.bgw.BgwApplication;
import com.luckwine.bgw.config.KdApiProperties;
import com.luckwine.bgw.model.enums.RequestType;
import com.luckwine.bgw.model.enums.ShipperCode;
import com.luckwine.bgw.util.EcryptUtils;
import com.luckwine.bgw.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 快递鸟预约取件下单接口
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BgwApplication.class)
@WebAppConfiguration
public class KdApiCreateOrderTest {

    @Autowired
    private KdApiProperties kdApiProperties;

    @Test
    public void orderOnlineByJson() throws Exception{

        JSONObject requestObj = new JSONObject();
        // 订单编号(自定义，不可重复)
        requestObj.put("OrderCode", "YYQJ20181115001");
        // 快递公司编码
        requestObj.put("ShipperCode", ShipperCode.YTO.getCode());
        // 运费支付方式:1- 现付 2- 到付 3- 月结 4- 第三方付(仅 SF 支持)
        requestObj.put("PayType", "1");
        // 详细快递类型
        requestObj.put("ExpType", "1");
        // 快递运费
        requestObj.put("Cost", "1");
        // 其他费用
        requestObj.put("OtherCost", "1");

        JSONObject senderObj = new JSONObject();
        // 发件人公司
        senderObj.put("Company", "LV");
        // 发件人
        senderObj.put("Name", "Taylor");
        // 手机
        senderObj.put("Mobile", "17520402729");
        // 发件省
        senderObj.put("ProvinceName", "广东省");
        // 发件市
        senderObj.put("CityName", "广州市");
        // 发件区/县
        senderObj.put("ExpAreaName", "萝岗区");
        // 发件人详细地址
        senderObj.put("Address", "映日路科学大道231-233号");
        // 发件人信息
        requestObj.put("Sender",senderObj);

        JSONObject receiverObj = new JSONObject();
        // 收件人公司
        receiverObj.put("Company", "LV");
        // 收件人
        receiverObj.put("Name", "Taylor");
        // 手机
        receiverObj.put("Mobile", "17520402729");
        // 收件省
        receiverObj.put("ProvinceName", "北京");
        // 收件市
        receiverObj.put("CityName", "北京");
        // 收件区/县
        receiverObj.put("ExpAreaName", "朝阳区");
        // 收件人详细地址
        receiverObj.put("Address", "三里屯街道雅秀大厦");
        // 收件人信息
        requestObj.put("Receiver",receiverObj);

        JSONObject commodityObj = new JSONObject();
        // 商品名称
        commodityObj.put("GoodsName", "鞋子");
        // 商品件数
        commodityObj.put("GoodsQuantity", "1");
        // 商品重量 kg
        commodityObj.put("GoodsWeight", "1");
        List<JSONObject> commodityObjList = new ArrayList<JSONObject>();
        commodityObjList.add(commodityObj);
        // 信息
        requestObj.put("Commodity",commodityObjList);

        JSONObject addService = new JSONObject();
        addService.put("Name","COD");
        addService.put("Value","1020");
        List<JSONObject> addServiceList = new ArrayList<JSONObject>();
        addServiceList.add(addService);
        requestObj.put("AddService",addServiceList);

        // 包裹总重量 kg
        requestObj.put("Weight", "1");
        // 包裹数
        requestObj.put("Quantity", "1");
        // 包裹总体积 m3
        requestObj.put("Volume", "0");
        // 备注
        requestObj.put("Remark", "小心轻放");

        JSONObject commodity = new JSONObject();
        commodity.put("GoodsName","鞋子");
        commodity.put("Goodsquantity","1");
        commodity.put("GoodsWeight","1.0");
        List<JSONObject> commodityList = new ArrayList<JSONObject>();
        commodityList.add(commodity);
        requestObj.put("Commodity",commodityList);

        System.out.println(requestObj.toString());

        Map<String, String> params = new HashMap<String, String>();
        //params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("RequestData", EcryptUtils.urlEncoder(requestObj.toString(), "UTF-8"));

        params.put("EBusinessID", kdApiProperties.getEbusinessid());
        params.put("RequestType", RequestType.CreateOrder.getCode());
        //String dataSign=encrypt(requestData, AppKey, "UTF-8");
        String dataSign=EcryptUtils.encrypt(requestObj.toString(), kdApiProperties.getApikey(), "UTF-8");

        params.put("DataSign", EcryptUtils.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result= HttpUtils.sendPost(kdApiProperties.getOrderurl(), params);

        //TODO 根据公司业务处理返回的信息......

        System.out.println(result);
    }


}
