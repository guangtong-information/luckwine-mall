package com.luckwine.marketing.service;

import com.luckwine.marketing.MarketingApplication;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.MarketingCouponReq;
import com.luckwine.marketing.model.request.scheme.GenerateCouponsReq;
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

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MarketingApplication.class)
@WebAppConfiguration
public class GenerateCouponsServiceTest {

    @Autowired
    private MarketingSchemeService generateCouponsService;
    @Autowired
    private MarketingCouponService marketingCouponService;
    @Test
    public void testGenerateCoupons() {
//        System.out.println((Math.random() * 9 + 1) * 1000);
//        Random random = new Random();
//        int randomNumber = RandomUtil.randomInt(1000, 10000);
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(RandomUtil.randomInt(1000, 10000));
//        }
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String formatDate = localDateTime.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
//        System.out.println(format);
//        String couponNum = formatDate + randomNumber;
//        System.out.println(couponNum);
//        System.out.println(SecureUtil.md5(couponNum));
//        System.out.println(SecureUtil.md5(couponNum).substring(8, 24));
        CommonRequest<GenerateCouponsReq> couponsReqCommonRequest = new CommonRequest<>();
        GenerateCouponsReq generateCouponsReq = new GenerateCouponsReq();
        generateCouponsReq.setSchemeId("1");

        couponsReqCommonRequest.setRequest(generateCouponsReq);
        generateCouponsService.generateCoupons(couponsReqCommonRequest);

    }


    @Test
    public void queryCouponPage() {
        CommonQueryPageRequest<MarketingCouponReq> request  = new CommonQueryPageRequest<>();

        MarketingCouponReq marketingScheme = new MarketingCouponReq();


        marketingScheme.setSchemeName("TEST1");

        request.setRequest(marketingScheme);
        request.setPageSize(5);
        request.setPageNo(1);
        CommonQueryPageResponse<List<MarketingCoupon>> responseCommonResponse = marketingCouponService.queryCouponPage(request);

        System.out.println("responseCommonResponse:" + responseCommonResponse);

    }

}
