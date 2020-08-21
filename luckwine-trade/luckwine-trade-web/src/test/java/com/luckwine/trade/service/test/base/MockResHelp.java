package com.luckwine.trade.service.test.base;

import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.marketing.model.request.enums.MarketingExpensesEnum;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockResHelp {

    /**
     * 操作库存成功
     *
     * @return
     */
    public static CommonResponse<Boolean> stockModifySuccess() {
        CommonResponse<Boolean> res = new CommonResponse<Boolean>();
        res.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        res.setContent("模拟" + ResponseCodeConstant.SUCCESS.getResponseDesc());
        res.setResponse(true);

        return res;
    }

    /**
     * 使用优惠券成功
     *
     * @return
     */
    public static CommonResponse<List<UserMarketingCouponResq>> userCouponsSuccess() {
        CommonResponse<List<UserMarketingCouponResq>> res = new CommonResponse<>();
        res.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        res.setContent("模拟" + ResponseCodeConstant.SUCCESS.getResponseDesc());

        List<UserMarketingCouponResq> coupon=new ArrayList<>();

        UserMarketingCouponResq userMarketingCouponResq=new UserMarketingCouponResq();
        List<Long> skuIdSubList=new ArrayList<>();
        skuIdSubList.add(10000093L);
        skuIdSubList.add(10000094L);
        userMarketingCouponResq.setSkuIdSubList(skuIdSubList);
        userMarketingCouponResq.setCouponNum("AAA");
        userMarketingCouponResq.setCreateTime(new Date());
        userMarketingCouponResq.setDiscountAmount(new BigDecimal(3));
        String cap_seq = SequenceUtil.genSequence(SequenceCode.CAPITAL, "1");
        userMarketingCouponResq.setRequestSeq(cap_seq);
        userMarketingCouponResq.setExtTrsSeq("trade11111");
        userMarketingCouponResq.setSchemeId("1");
        userMarketingCouponResq.setStatus(MarketingExpensesEnum.STATUS_SUCCESS.getCode());
        String back_seq = SequenceUtil.genSequence(SequenceCode.PAYMENT, "1");
        userMarketingCouponResq.setMarketOrderno(back_seq);
        userMarketingCouponResq.setUserAccount("15918837235");
        coupon.add(userMarketingCouponResq);

        res.setResponse(coupon);

        return res;
    }

    /**
     * 操作库存失败
     *
     * @return
     */
    public static CommonResponse<Boolean> stockModifyFail() {
        CommonResponse<Boolean> res = new CommonResponse<Boolean>();
        res.setCode(ResponseCodeConstant.DUBBO_FORBIDDEN_EXCEPTION.getResponseCode());
        res.setContent("模拟" + ResponseCodeConstant.DUBBO_FORBIDDEN_EXCEPTION.getResponseDesc());
        res.setResponse(true);

        return res;
    }

    /**
     * 查询sku详情失败
     *
     * @return
     */
    public static CommonResponse<List<SkuDetail>> skuDetailFail() {
        CommonResponse<List<SkuDetail>> res = new CommonResponse<List<SkuDetail>>();
        res.setCode(ResponseCodeConstant.DUBBO_TIME_OUT.getResponseCode());
        res.setContent("模拟" + ResponseCodeConstant.DUBBO_TIME_OUT.getResponseDesc());
        res.setResponse(null);

        return res;
    }

    /**
     * 查询sku详情失败
     *
     * @return
     */
    public static CommonResponse<List<SkuDetail>> skuDetailSuccess() {
        CommonResponse<List<SkuDetail>> res = new CommonResponse<List<SkuDetail>>();
        res.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        res.setContent("模拟" + ResponseCodeConstant.SUCCESS.getResponseDesc());

        List<SkuDetail> skuDetailList = new ArrayList<SkuDetail>();

        //商品1
        SkuDetail skuDetail = new SkuDetail();
        skuDetail.setSpuId("1537350246406");
        skuDetail.setGoodsName("iphonex s");
        skuDetail.setCategoryId(26L);
        skuDetail.setCategoryName("手机");
        skuDetail.setBrandId(2116L);
        skuDetail.setBrandName("苹果");
        skuDetail.setTitle("模拟测试");
        skuDetail.setProps("8:10;9:14");
        skuDetail.setPropsStr("内存:64GB;颜色:深空灰色");
        skuDetail.setPicPath("https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/tmp/luckwine/oss/2018/09/19/10/51/0ace3ed19582dbe6.jpg");
        skuDetail.setDetail("模拟测试");
        skuDetail.setStatus(SpuStatus.ON_SALE.name());
        skuDetail.setSpuCreateTime(new Date());

        skuDetail.setSkuId(10000093L);
        skuDetail.setSkuName("iphone xs 64G");
        skuDetail.setSkuPropsStr("内存:64GB;颜色:深空灰色");
        skuDetail.setSkuProps("8:10;9:14");
        skuDetail.setQuantity(1000L);

        skuDetail.setSaleCount(1L);
        skuDetail.setPrice(new BigDecimal("20.63"));
        skuDetail.setSkuStatus(SkuStatus.ON_SALE.name());

        skuDetailList.add(skuDetail);

        //商品2
        skuDetail = new SkuDetail();
        skuDetail.setSpuId("1537350246406");
        skuDetail.setGoodsName("华为手机");
        skuDetail.setCategoryId(26L);
        skuDetail.setCategoryName("手机");
        skuDetail.setBrandId(2116L);
        skuDetail.setBrandName("苹果");
        skuDetail.setTitle("模拟测试");
        skuDetail.setProps("8:10;9:14");
        skuDetail.setPropsStr("内存:64GB;颜色:深空灰色");
        skuDetail.setPicPath("https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/tmp/luckwine/oss/2018/09/19/10/51/0ace3ed19582dbe6.jpg");
        skuDetail.setDetail("模拟测试");
        skuDetail.setStatus(SpuStatus.ON_SALE.name());
        skuDetail.setSpuCreateTime(new Date());

        skuDetail.setSkuId(10000094L);
        skuDetail.setSkuName("华为荣耀10");
        skuDetail.setSkuPropsStr("内存:64GB;颜色:深空灰色");
        skuDetail.setSkuProps("8:10;9:14");
        skuDetail.setQuantity(1000L);

        skuDetail.setSaleCount(1L);
        skuDetail.setPrice(new BigDecimal("44.23"));
        skuDetail.setSkuStatus(SkuStatus.ON_SALE.name());
        skuDetailList.add(skuDetail);

        res.setResponse(skuDetailList);

        return res;
    }

}
