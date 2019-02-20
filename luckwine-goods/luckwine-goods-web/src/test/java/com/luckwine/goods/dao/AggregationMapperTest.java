package com.luckwine.goods.dao;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.base.vo.SkuDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class AggregationMapperTest {

    @Autowired
    private AggregationMapper aggregationMapper;

    @Test
    public void findBySpuId() {
        List<SkuDetail> list  =   aggregationMapper.findBySpuId("1537350246406");
        log.info("{}", list);
    }


    @Test
    public void findBySkuIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(10000100L);
        ids.add(10000101L);
        List<SkuDetail> list = aggregationMapper.findBySkuIds(ids);
        log.info("{}", list);
    }
}