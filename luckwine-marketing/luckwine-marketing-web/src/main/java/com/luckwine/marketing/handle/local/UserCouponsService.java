package com.luckwine.marketing.handle.local;

import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.dao.MarketingExpensesMapper;
import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.base.MarketingExpenses;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.OrderAvailableCouponReq;
import com.luckwine.marketing.model.request.coupon.UserMarketingCouponReq;
import com.luckwine.marketing.model.request.enums.MarketingCouponEnum;
import com.luckwine.marketing.model.request.enums.MarketingExpensesEnum;
import com.luckwine.marketing.model.response.OrderAvailableCouponResp;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserCouponsService extends SingleTemplate<UserMarketingCouponReq, List<UserMarketingCouponResq>> {

    @Resource
    private MarketingCouponMapper marketingCouponMapper;

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;

    @Resource
    private MarketingExpensesMapper marketingExpensesMapper;

    @Autowired
    private OrderAvailableCouponsService orderAvailableCouponsService;

    @Override
    protected List<UserMarketingCouponResq> callInner(CommonRequest<UserMarketingCouponReq> request) throws Exception {

        // 第一步：调用“查询订单可用优惠券”接口，验证优惠劵是否可用
        //组装请求参数
        CommonRequest<OrderAvailableCouponReq> orderAvailableCouponCommonRequest = new CommonRequest<OrderAvailableCouponReq>();
        orderAvailableCouponCommonRequest.setAppName(request.getAppName());
        orderAvailableCouponCommonRequest.setChannelCode(request.getChannelCode());
        orderAvailableCouponCommonRequest.setOperLevel(request.getOperLevel());
        orderAvailableCouponCommonRequest.setTraceId(request.getTraceId());
        OrderAvailableCouponReq orderAvailableCouponReq = new OrderAvailableCouponReq();
        orderAvailableCouponReq.setLoginAccount(request.getRequest().getLoginAccount());
        //验证优惠券是否可用
        for (MarketRequestSeq marketRequestSeq:
                request.getRequest().getMarketRequestSeqList()) {
            orderAvailableCouponReq.setSkuIdList(marketRequestSeq.getSkuIdSubList());
            orderAvailableCouponCommonRequest.setRequest(orderAvailableCouponReq);
            CommonResponse<List<OrderAvailableCouponResp>> orderAvailableCoupons = orderAvailableCouponsService.call(orderAvailableCouponCommonRequest);
            if(orderAvailableCoupons != null && orderAvailableCoupons.getResponse() != null
                && orderAvailableCoupons.getResponse().size() > 0){
                boolean isExist = false;
                for (OrderAvailableCouponResp  orderAvailableCouponResp:
                    orderAvailableCoupons.getResponse()) {
                    if(orderAvailableCouponResp.getCouponNum().equals(marketRequestSeq.getCouponNum())){
                        isExist = true;
                    }
                }
                if(!isExist){
                    throw new Exception("存在不可用的优惠券");
                }
            }
        }
        List<UserMarketingCouponResq> userMarketingCouponResqs = new ArrayList<UserMarketingCouponResq>();

        for (MarketRequestSeq marketRequestSeq:
                request.getRequest().getMarketRequestSeqList()) {

            //第二步：使用优惠券
            //根据优惠券编码更新优惠券
            Example example = new Example(MarketingCoupon.class);
            example.createCriteria()
                    .andEqualTo("couponNum", marketRequestSeq.getCouponNum());
            MarketingCoupon coupon = marketingCouponMapper.selectOneByExample(example);
            coupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_USED.getCode());
            coupon.setUseTime(new Date());
            marketingCouponMapper.updateByExample(coupon, example);
            MarketingScheme marketingScheme = marketingSchemeMapper.selectByPrimaryKey(coupon.getSchemeId());
            //第三步：生成营销流水，并返回营销流水等信息
            MarketingExpenses marketingExpenses = new MarketingExpenses();
            marketingExpenses.setMarketOrderno(UUID.randomUUID().toString());
            marketingExpenses.setExtTrsSeq(request.getRequest().getOrderNo());
            marketingExpenses.setRequestSeq(marketRequestSeq.getRequestSeq());
            marketingExpenses.setCreateTime(new Date());
            marketingExpenses.setSchemeId(coupon.getSchemeId());
            marketingExpenses.setCouponNum(marketRequestSeq.getCouponNum());
            marketingExpenses.setUserAccount(request.getRequest().getLoginAccount());
            marketingExpenses.setDiscountAmount(marketingScheme.getDiscountAmount());
            marketingExpenses.setStatus(MarketingExpensesEnum.STATUS_SUCCESS.getCode());
            marketingExpensesMapper.insert(marketingExpenses);

            UserMarketingCouponResq userMarketingCouponResq = new UserMarketingCouponResq();
            userMarketingCouponResq.setMarketOrderno(marketingExpenses.getMarketOrderno());
            userMarketingCouponResq.setExtTrsSeq(request.getRequest().getOrderNo());
            userMarketingCouponResq.setRequestSeq(marketRequestSeq.getRequestSeq());
            userMarketingCouponResq.setCreateTime(marketingExpenses.getCreateTime());
            userMarketingCouponResq.setSchemeId(coupon.getSchemeId());
            userMarketingCouponResq.setCouponNum(marketRequestSeq.getCouponNum());
            userMarketingCouponResq.setUserAccount(request.getRequest().getLoginAccount());
            userMarketingCouponResq.setDiscountAmount(marketingScheme.getDiscountAmount());
            userMarketingCouponResq.setStatus(MarketingExpensesEnum.STATUS_SUCCESS.getCode());
            userMarketingCouponResq.setSkuIdSubList(marketRequestSeq.getSkuIdSubList());
            userMarketingCouponResqs.add(userMarketingCouponResq);
        }
        return userMarketingCouponResqs;
    }
}
