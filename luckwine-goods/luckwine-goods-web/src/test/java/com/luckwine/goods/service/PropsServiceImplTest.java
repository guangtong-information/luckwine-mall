package com.luckwine.goods.service;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.request.props.PropsKeyPageRequest;
import com.luckwine.goods.service.PropsServiceImpl;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
/**
 * Title: 商品属性 测试类
 * Description: 商品属性 测试类
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class PropsServiceImplTest {

    @Autowired
    private PropsServiceImpl propsService;


    @Test
    public void getKeys() {
        String key = "";
        PropsKeyPageRequest propsKeyPageRequest = new PropsKeyPageRequest();
        propsKeyPageRequest.setKey(key);
        CommonQueryPageRequest<PropsKeyPageRequest> request = new CommonQueryPageRequest<>();
        request.setRequest( propsKeyPageRequest );
        CommonQueryPageResponse<List<PropsKey>> response = propsService.pageKey( request );
        List<PropsKey> list = response.getResponse();

        System.out.println( response );


        if( null != list && list.size() > 0 ){
            for( PropsKey propsKey : list ){
                System.out.println( propsKey.getKey() );
            }
        }

    }



}