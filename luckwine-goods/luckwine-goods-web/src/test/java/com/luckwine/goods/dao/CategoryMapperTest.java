package com.luckwine.goods.dao;

import com.luckwine.goods.GoodsApplication;
import com.luckwine.goods.model.base.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
@Slf4j
public class CategoryMapperTest {


    @Autowired
    private CategoryMapper categoryMapper;


    @Test
    public void  test (){
       List<Category> list =  categoryMapper.selectAll();
       log.info("{}", list);
    }

}