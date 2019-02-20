package com.luckwine.goods.utils;

import com.luckwine.goods.GoodsApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class GoodsUtilsTest {

    @Autowired
    private GoodsUtils goodsUtils;


    @Test
    public void getBrands() {
        Set<Long> list = new HashSet<>();
        list.add(6L);
        list.add(7L);
        list.add(8L);
        list.add(8L);
        goodsUtils.getBrands(list);
    }

    @Test
    public void getCategorys(){
        Set<Long> list = new HashSet<>();
        list.add(6L);
        list.add(7L);
        list.add(8L);
        list.add(8L);
        goodsUtils.getCategorys(list);
    }


    @Test
    public void getDbKeyList() {
        Set<Long> list = new HashSet<>();
        list.add(6L);
        list.add(7L);
        list.add(8L);
        list.add(8L);
        goodsUtils.getDbKeyList(list);
    }


    @Test
    public void getDbValueList() {
        Set<Long> list = new HashSet<>();
        list.add(6L);
        list.add(7L);
        list.add(8L);
        list.add(8L);
        goodsUtils.getDbValueList(list);
    }

}