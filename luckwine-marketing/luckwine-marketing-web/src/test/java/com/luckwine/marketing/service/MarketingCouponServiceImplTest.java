package com.luckwine.marketing.service;

import cn.hutool.core.date.DateUtil;
import com.luckwine.marketing.MarketingApplication;
import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.OrderAvailableCouponReq;
import com.luckwine.marketing.model.request.coupon.UserMarketingCouponReq;
import com.luckwine.marketing.model.request.enums.MarketingCouponEnum;
import com.luckwine.marketing.model.response.OrderAvailableCouponResp;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MarketingApplication.class)
@WebAppConfiguration
public class MarketingCouponServiceImplTest {

    @Resource
    private MarketingCouponService marketingCouponService;

    @Resource
    private MarketingCouponMapper marketingCouponMapper;


    @Test
    public void testOrderAvailableCoupons() {
        CommonRequest<OrderAvailableCouponReq> request = new CommonRequest<OrderAvailableCouponReq>();

        OrderAvailableCouponReq req = new OrderAvailableCouponReq();
        req.setLoginAccount("1");
        List<Long> skuIdList = new ArrayList<Long>();
        skuIdList.add(10004207L);
        skuIdList.add(10004206L);
        req.setSkuIdList(skuIdList);
        request.setRequest(req);

        CommonResponse<List<OrderAvailableCouponResp>> response = marketingCouponService.orderAvailableCoupons(request);
        List<OrderAvailableCouponResp> list = response.getResponse();
        if(list == null || list.size() == 0) {
            System.out.println("-------------------------------------------");
            System.out.println("没有优惠券");

        }else {
            for (OrderAvailableCouponResp resp : list) {
                System.out.println("-------------------------------------------");
                System.out.println(resp);
            }
        }
    }


    @Test
    public void testUserCoupons() {
        CommonRequest<UserMarketingCouponReq> request = new CommonRequest<UserMarketingCouponReq>();
        UserMarketingCouponReq req = new UserMarketingCouponReq();
        req.setLoginAccount("1");
        req.setOrderNo("1");
        MarketRequestSeq seq = new MarketRequestSeq();
        seq.setRequestSeq("1111");
        seq.setCouponNum("111");
        List<Long> skuIdList = new ArrayList<Long>();

        skuIdList.add(10004207L);
        skuIdList.add(10004206L);
        seq.setSkuIdSubList(skuIdList);
        List<MarketRequestSeq> seqs = new ArrayList<MarketRequestSeq>();
        seqs.add(seq);
        req.setMarketRequestSeqList(seqs);
        request.setRequest(req);
        CommonResponse<List<UserMarketingCouponResq>> response = marketingCouponService.userCoupons( request);

        List<UserMarketingCouponResq> list = response.getResponse();
        for (UserMarketingCouponResq resp: list ) {
            System.out.println("-------------------------------------------");
            System.out.println(resp);
        }
    }

    @Test
    public void testOverdueSerarch() {
        Example example = new Example(MarketingCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("scheme_id in (select scheme_id from marketing_scheme where '" + DateUtil.now() + "' >= effective_endtime)");

        List<MarketingCoupon> list = marketingCouponMapper.selectByExample(example);
        System.out.println(list);
    }

    @Test
    public void updateOverdue() {
        Example example = new Example(MarketingCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("scheme_id in (select scheme_id from marketing_scheme where '" + DateUtil.now() + "' >= effective_endtime)");

        MarketingCoupon marketingCoupon = new MarketingCoupon();
        marketingCoupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_OVERDUE.getCode());
        marketingCouponMapper.updateByExampleSelective(marketingCoupon, example);
    }

    @Test
    public void receiveCoupon() throws Exception {
        CommonRequest<MarketingCoupon> request = new CommonRequest<>();
        MarketingCoupon marketingCoupon = new MarketingCoupon();
//        marketingCoupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_OVERDUE.getCode());
        marketingCoupon.setCouponId("000a7295-54ea-4ba8-a133-b5db7fe57ad3");
        marketingCoupon.setLoginAccount("123456");
        request.setRequest(marketingCoupon);
        marketingCouponService.receiveCoupon(request);
    }
}
