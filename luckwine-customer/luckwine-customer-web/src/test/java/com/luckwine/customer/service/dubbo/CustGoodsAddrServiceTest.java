package com.luckwine.customer.service.dubbo;

import com.luckwine.customer.CustomerApplicationTest;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.customer.service.CustGoodsAddrService;

import com.luckwine.parent.entitybase.request.CommonRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;

public class CustGoodsAddrServiceTest extends CustomerApplicationTest {

    @DubboReference
    private CustGoodsAddrService custGoodsAddrService;
    @Test
    public void testInsertCustGoodsAddr(){
        CommonRequest<CustGoodsAddr> request = new CommonRequest<CustGoodsAddr>();
        CustGoodsAddr addr = new CustGoodsAddr();
        addr.setAddress("广州天河区");
        addr.setCity("广州");
        addr.setLoginAccount("test3");
        addr.setConsignee("haohao");
        addr.setProvince("广东省");
        addr.setDistrict("天河");
        addr.setPostcode("510000");
        addr.setMoblie("1341617889");
        addr.setTelephone("02010086");
        addr.setGoodsAddrId("00003");

        request.setRequest(addr);

        custGoodsAddrService.insertCustGoodsAddr(request);

    }

    @Test
    public void testUpdateCustGoodsAddr(){
        CommonRequest<CustGoodsAddr> request = new CommonRequest<CustGoodsAddr>();
        CustGoodsAddr addr = new CustGoodsAddr();
        addr.setAddress("广州天河区2");

        addr.setConsignee("haohao2");
        //addr.setLoginAccount("test");

        addr.setGoodsAddrId("00002");

        request.setRequest(addr);

        custGoodsAddrService.updateCustGoodsAddr(request);

    }

    @Test
    public void testDelCustGoodsAddr(){
        CommonRequest<CustGoodsAddr> request = new CommonRequest<CustGoodsAddr>();
        CustGoodsAddr deleteAddr = new CustGoodsAddr();

        deleteAddr.setGoodsAddrId("00013");

        request.setRequest(deleteAddr);

        custGoodsAddrService.delCustGoodsAddr(request);

    }

}
