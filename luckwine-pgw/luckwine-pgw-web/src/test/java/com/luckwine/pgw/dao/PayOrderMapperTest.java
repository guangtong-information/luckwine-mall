package com.luckwine.pgw.dao;

import com.luckwine.pgw.PgwApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PgwApplication.class)
@Slf4j
public class PayOrderMapperTest {

    @Autowired
    private PayOrderMapper payOrderMapper;


    @Test
    public void test() {
        log.info("{}", payOrderMapper.selectAll());
    }

    @Test
    public void testSelectPayOrders() {
        List<String> list = payOrderMapper.selectPayOrders("2018-10-14");
        log.info("resultdata:{}", list);
    }
}