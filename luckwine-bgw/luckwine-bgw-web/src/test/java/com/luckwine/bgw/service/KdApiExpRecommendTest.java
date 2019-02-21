package com.luckwine.bgw.service;

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
 * 快递鸟智选物流接口
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BgwApplication.class)
@WebAppConfiguration
public class KdApiExpRecommendTest {

    @Autowired
    private KdApiProperties kdApiProperties;

    @Test
    public void getExpRecommendByJson() throws Exception{
        String requestData= "{'MemberID':'123456','WarehouseID':'1','Detail':[{'OrderCode':'12345','IsCOD':0,'Sender':{'ProvinceName':'广东省','CityName':'广州','ExpAreaName':'龙岗区','Subdistrict':'布吉街道','Address':'518000'},'Receiver':{'ProvinceName':'广东','CityName':'梅州','ExpAreaName':'丰顺','Subdistrict':'布吉街道','Address':'518000'},'Goods':[{'ProductName':'包','Volume':'','Weight':'1'}]},{'OrderCode':'12346','IsCOD':0,'Sender':{'ProvinceName':'广东省','CityName':'广州','ExpAreaName':'龙岗区','Subdistrict':'布吉街道','Address':'518000'},'Receiver':{'ProvinceName':'湖南','CityName':'长沙','ExpAreaName':'龙岗区','Subdistrict':'布吉街道','Address':'518000'},'Goods':[{'ProductName':'包','Volume':'','Weight':'1'}]}]}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", EcryptUtils.urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", kdApiProperties.getEbusinessid());
        params.put("RequestType", RequestType.ExpRecommend.getCode() );
        String dataSign=EcryptUtils.encrypt(requestData, kdApiProperties.getApikey(), "UTF-8");
        params.put("DataSign", EcryptUtils.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result= HttpUtils.sendPost(kdApiProperties.getEbusinessorderurl(), params);

        System.out.println(result) ;
    }
}
