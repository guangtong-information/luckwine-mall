package com.luckwine.bgw.service;

import com.alibaba.fastjson.JSONObject;
import com.luckwine.bgw.BgwApplication;
import com.luckwine.bgw.config.KdApiProperties;
import com.luckwine.bgw.model.enums.RequestType;
import com.luckwine.bgw.util.EcryptUtils;
import com.luckwine.bgw.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * 快递鸟预约取件订单取消接口
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BgwApplication.class)
@WebAppConfiguration
public class KdApiRemoveOrderTest {

    @Autowired
    private KdApiProperties kdApiProperties;

    @Test
    public void removeOrderOnlineByJson() throws Exception{

        JSONObject requestObj = new JSONObject();

        requestObj.put("OrderCode","YYQJ20181115001");
        requestObj.put("ShipperCode","YTO");

        System.out.println(requestObj.toString());

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", EcryptUtils.urlEncoder(requestObj.toString(), "UTF-8"));

        params.put("EBusinessID", kdApiProperties.getEbusinessid());
        params.put("RequestType", RequestType.RemoveOrder.getCode());
        String dataSign=EcryptUtils.encrypt(requestObj.toString(), kdApiProperties.getApikey(), "UTF-8");

        params.put("DataSign", EcryptUtils.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result= HttpUtils.sendPost(kdApiProperties.getOrderurl(), params);

        System.out.println(result);
    }
}
