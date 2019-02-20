package com.luckwine.customer.service.dubbo;

import com.luckwine.customer.CustomerApplication;
import com.luckwine.customer.handle.local.CustGoodsAddrPageService;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

/**
 * @ClassName CustGoodsServiceTest
 * @Description 收货地址 测试类
 * @Author yewenqing 叶文清
 * @Date 2018/10/3 17:15
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerApplication.class)
public class CustGoodsServiceTest {

    @Autowired
    public CustGoodsAddrPageService custGoodsAddrPageService;

    /**
     * 功能描述: 测试获取客户收货地址(分页)
     * @Author yewenqing 叶文清
     * @Date 2018-10-04 12:48
     */
    @Test
    public void testQueryCustGoodsAddrPage(){
        CommonQueryPageRequest<CustGoodsAddr> request = new CommonQueryPageRequest<>();
        request.setPageNo(1);
        request.setPageSize(10);
        CustGoodsAddr custGoodsAddr = new CustGoodsAddr();
            //设置查询条件
            //custGoodsAddr.setAddress( "广州天河区2" );
        request.setRequest( custGoodsAddr );

        //调用local的service方法测试
        CommonQueryPageResponse<List<CustGoodsAddr>> queryPageResponse = custGoodsAddrPageService.call(request);

        //打印查询返回对象
        System.out.println( "=========================================================================================================================");
        System.out.println( "queryPageResponse: " + queryPageResponse );
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");

        //获取查询的结果集合List
        List<CustGoodsAddr> list = queryPageResponse.getResponse();
        System.out.println( "=========================================================================================================================");
        System.out.println( "queryPageResponse: " + queryPageResponse );
        System.out.println( "查询总数: " + list.size());
        for( CustGoodsAddr obj : list ){
            System.out.println( "obj = " + obj );
        }
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
        System.out.println( "=========================================================================================================================");
    }

}
