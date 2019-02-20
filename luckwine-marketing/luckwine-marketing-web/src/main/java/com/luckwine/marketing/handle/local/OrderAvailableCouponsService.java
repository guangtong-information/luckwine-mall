package com.luckwine.marketing.handle.local;

import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.handle.remote.SkuListBySkuIdService;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.enums.MarketingCouponEnum;
import com.luckwine.marketing.model.request.coupon.OrderAvailableCouponReq;
import com.luckwine.marketing.model.request.enums.MarketingSchemeEnum;
import com.luckwine.marketing.model.response.OrderAvailableCouponResp;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderAvailableCouponsService extends SingleTemplate<OrderAvailableCouponReq, List<OrderAvailableCouponResp>> {

    @Autowired
    private SkuListBySkuIdService skuListBySkuIdService;

    @Resource
    private MarketingCouponMapper marketingCouponMapper;

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;

    @Override
    protected List<OrderAvailableCouponResp> callInner(CommonRequest<OrderAvailableCouponReq> request) throws Exception {

        // 第一步：根据SKUID，调用Goods模块，查询商品的价格
        //调用服务，获取商品
        CommonRequest<SkuDetailGetByIdsRequest> remoteRequest = new CommonRequest<SkuDetailGetByIdsRequest>();
        remoteRequest.setAppName(request.getAppName());
        remoteRequest.setChannelCode(request.getChannelCode());
        remoteRequest.setOperLevel(request.getOperLevel());
        remoteRequest.setTraceId(request.getTraceId());
        SkuDetailGetByIdsRequest skuDetailGetByIdsRequest = new SkuDetailGetByIdsRequest();
        skuDetailGetByIdsRequest.setSkuIds(request.getRequest().getSkuIdList());
        remoteRequest.setRequest(skuDetailGetByIdsRequest);
        CommonResponse<List<SkuDetail>> skuDetailCommonResponse = skuListBySkuIdService.call(remoteRequest);
        List<SkuDetail> skuList = skuDetailCommonResponse.getResponse();

        if(skuList == null || skuList.size() == 0){
            throw new Exception("根据skuId找不到对应商品");
        }
        //计算合计金额
        BigDecimal total = new BigDecimal(0);
        for (SkuDetail sku:skuList ) {
            total = total.add(sku.getPrice());
        }
        // 第二步：根据用户ID，商品单价，返回符合条件(包括校验营销方案状态-启用)的可用优惠券
        //获取用户可用的优惠券
        MarketingCoupon coupon = new MarketingCoupon();
        coupon.setLoginAccount(request.getRequest().getLoginAccount());//设置用户
        coupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_NOUSED.getCode());//设置查询状态为可用
        List<MarketingCoupon> coupons = marketingCouponMapper.select(coupon);//获取优惠
        //判断是否为空
        if(coupons == null || coupons.size() == 0){
            return null;
        }
        List<OrderAvailableCouponResp> orderAvailableCouponResp = null;//需要返回的优惠
        //校验营销方案状态-启用
        // TODO 本期只做全场商品、本期只做卡券
        MarketingCoupon marketingCouponResult = null; //最优惠方案的优惠券
        MarketingScheme marketingSchemeResult = null;//最优惠方案
        if(coupons.size() == 1){
            marketingCouponResult = coupons.get(0);
        }else{
            for (MarketingCoupon entity : coupons){
                //如果没有最优惠方案，则默认当前为最优惠方案，然后再与其他优惠方案进行比较
                if(marketingCouponResult == null) {
                    marketingCouponResult = entity;
                }
                //获取优惠方案
                MarketingScheme schemeRes = getMarketingSchemeById(entity.getSchemeId());
                if(marketingSchemeResult == null){
                    marketingSchemeResult = schemeRes;
                }
                //判断合计金额大于等于优惠券的使用金额
                if(schemeRes != null
                        && total.compareTo(schemeRes.getUseAmount()) > -1){
                    //获取优惠最高的优惠券
                    for (MarketingCoupon entityTemp : coupons){

                        MarketingScheme schemeResCompare = getMarketingSchemeById(entityTemp.getSchemeId());
                        //如果其他优惠方案比当前的更优惠，则替换当前优惠方案
                        if(calculateDiscountAmount(total,marketingSchemeResult).compareTo(calculateDiscountAmount(total,schemeResCompare)) == -1){
                            marketingCouponResult = entityTemp;
                        }
                    }

                }
            }
        }
        orderAvailableCouponResp = new ArrayList<OrderAvailableCouponResp>();
        //设置值
        OrderAvailableCouponResp resp = new OrderAvailableCouponResp();
        resp.setCouponId(marketingCouponResult.getCouponId());
        resp.setCouponNum(marketingCouponResult.getCouponNum());
        resp.setLoginAccount(marketingCouponResult.getLoginAccount());
        resp.setSchemeId(marketingCouponResult.getSchemeId());
        resp.setCouponStat(marketingCouponResult.getCouponStat());
        resp.setCreateTime(marketingCouponResult.getCreateTime());
        resp.setGetTime(marketingCouponResult.getGetTime());
        resp.setUseTime(marketingCouponResult.getUseTime());
        resp.setSkuIdSubList(request.getRequest().getSkuIdList());
        orderAvailableCouponResp.add(resp);
        return orderAvailableCouponResp;
    }

    /**
     * 获取优惠方案
     * @param schemeId
     * @return
     */
    private MarketingScheme getMarketingSchemeById(String schemeId){
        MarketingScheme scheme = new MarketingScheme();
        OrderAvailableCouponResp resp = new OrderAvailableCouponResp();
        scheme.setSchemeId(schemeId);
        //TODO 本期只做全场商品
        scheme.setSchemeObj(MarketingSchemeEnum.SCHEME_OBJ_ALL.getCode());
        //TODO 本期只做卡券
        scheme.setSchemeType(MarketingSchemeEnum.SCHEME_TYPE_COUPON.getCode());
        //营销方案为可用
        scheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode());

        MarketingScheme schemeRes = marketingSchemeMapper.selectOne(scheme);
        return schemeRes;
    }


    /**
     * 计算优惠金额
     * @param totalAmount
     * @param marketingScheme
     * @return
     */
    private BigDecimal calculateDiscountAmount(BigDecimal totalAmount, MarketingScheme marketingScheme){
        BigDecimal discountAmount = new BigDecimal(0);
        if(marketingScheme != null
                && totalAmount.compareTo(marketingScheme.getUseAmount()) > -1){
            //判断规则：满额
            if(MarketingSchemeEnum.USE_RULE_FULL_AMOUNT.getCode().equals(marketingScheme.getUseRule())){
                //判断方式：直减
                if(MarketingSchemeEnum.DISCOUNT_UINT_RMB.getCode().equals(marketingScheme.getDiscountUnit())){
                    discountAmount = marketingScheme.getDiscountAmount();
                //判断方式：打折
                }else if(MarketingSchemeEnum.DISCOUNT_UINT_DISCOUNT.getCode().equals(marketingScheme.getDiscountUnit())){
                    discountAmount = totalAmount.multiply((new BigDecimal(1)).subtract(marketingScheme.getDiscountAmount())).setScale(1,BigDecimal.ROUND_DOWN);
                }
            //判断规则：满减
            }else if(MarketingSchemeEnum.USE_RULE_FULL_DISCOUNT.getCode().equals(marketingScheme.getUseRule())){
                BigDecimal count = totalAmount.divide(marketingScheme.getUseAmount(),0,BigDecimal.ROUND_DOWN);
                //判断方式：直减
                if(MarketingSchemeEnum.DISCOUNT_UINT_RMB.getCode().equals(marketingScheme.getDiscountUnit())){
                    discountAmount =  count.multiply(marketingScheme.getDiscountAmount());
                //判断方式：打折
                }else if(MarketingSchemeEnum.DISCOUNT_UINT_DISCOUNT.getCode().equals(marketingScheme.getDiscountUnit())){
                    discountAmount = count.multiply(totalAmount.multiply((new BigDecimal(1)).subtract(marketingScheme.getDiscountAmount())).setScale(1,BigDecimal.ROUND_DOWN)).setScale(1,BigDecimal.ROUND_DOWN);
                }
            }
        }
        return discountAmount;
    }

}
