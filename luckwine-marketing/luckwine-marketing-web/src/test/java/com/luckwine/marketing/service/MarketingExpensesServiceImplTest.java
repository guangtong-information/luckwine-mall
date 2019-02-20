package com.luckwine.marketing.service;

import com.luckwine.marketing.MarketingApplication;
import com.luckwine.marketing.model.expenses.MarketingExpensesReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MarketingApplication.class)
@WebAppConfiguration
public class MarketingExpensesServiceImplTest {

    @Resource
    private MarketingExpensesService marketingExpensesService;

    @Test
    public void testQueryMarketingExpenses() {

        CommonQueryPageRequest<MarketingExpensesReq> queryPageRequest = new CommonQueryPageRequest<>();

        queryPageRequest.setPageNo(1);

        queryPageRequest.setPageSize(1);

        System.out.println(marketingExpensesService.queryMarketingExpensesPage(queryPageRequest));

    }

}
