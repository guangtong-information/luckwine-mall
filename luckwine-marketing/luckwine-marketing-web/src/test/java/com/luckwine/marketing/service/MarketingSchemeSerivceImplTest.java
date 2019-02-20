package com.luckwine.marketing.service;

import com.luckwine.marketing.MarketingApplication;
import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.enums.MarketingSchemeEnum;
import com.luckwine.marketing.model.request.scheme.CouponCenterPageListReq;
import com.luckwine.marketing.model.request.scheme.QuerySchemeDetailReq;
import com.luckwine.marketing.model.response.CouponCenterPageListResp;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MarketingApplication.class)
@WebAppConfiguration
public class MarketingSchemeSerivceImplTest {

    @Autowired
    private MarketingSchemeService marketingSchemeService;

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;

    @Test
    public void getDetail()
    {
        CommonRequest<QuerySchemeDetailReq> request = new CommonRequest<>();
        QuerySchemeDetailReq querySchemeDetailReq = new QuerySchemeDetailReq();
        querySchemeDetailReq.setSchemeId("1");
        request.setRequest(querySchemeDetailReq);
        System.out.println(marketingSchemeService.querySchemeDetail(request).getResponse().getSchemeName());
    }

    @Test
    public void update() throws Exception {
        CommonRequest<MarketingScheme> request = new CommonRequest<>();
        MarketingScheme marketingScheme = new MarketingScheme();
        marketingScheme.setSchemeId("1");
        marketingScheme.setSchemeName("TEST222");
        request.setRequest(marketingScheme);
        marketingSchemeService.updateScheme(request);
    }

    @Test
    public void updateStat() throws Exception {
        Date date = new Date();

        MarketingScheme marketingScheme = new MarketingScheme();
        marketingScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_DISABLE.getCode());

        Example example = new Example(MarketingScheme.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLessThan("effectiveEndtime", date);

//        List<MarketingScheme> list = marketingSchemeMapper.selectByExample(example);
//        System.out.println(list);
        marketingSchemeMapper.updateByExampleSelective(marketingScheme, example);
    }


    @Test
    public void querySchemePage() {
        CommonQueryPageRequest<CouponCenterPageListReq> request = new CommonQueryPageRequest<>();

        CouponCenterPageListReq marketingScheme = new CouponCenterPageListReq();


        marketingScheme.setSchemeName("TEST1");

        request.setRequest(marketingScheme);
        request.setPageSize(5);
        request.setPageNo(1);
        CommonQueryPageResponse<List<CouponCenterPageListResp>> responseCommonResponse = marketingSchemeService.querySchemePage(request);

        System.out.println("responseCommonResponse:" + responseCommonResponse);

    }

}
