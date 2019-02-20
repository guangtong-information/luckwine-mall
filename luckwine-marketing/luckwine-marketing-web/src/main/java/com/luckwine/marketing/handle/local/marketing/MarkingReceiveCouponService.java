package com.luckwine.marketing.handle.local.marketing;

import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.enums.MarketingCouponEnum;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.DataBaseTemplate;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MarkingReceiveCouponService extends DataBaseTemplate<MarketingCoupon,Boolean> {

    @Resource
    private MarketingCouponMapper MarkingCouponMapper;

    @Override
    protected Boolean callInner(CommonRequest<MarketingCoupon> request) throws Exception {
        MarketingCoupon marketingCoupon = request.getRequest();


        marketingCoupon.setGetTime(new Date());
        marketingCoupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_NOUSED.getCode());
        int row = MarkingCouponMapper.updateByPrimaryKeySelective(marketingCoupon);

        if(row>0)
            return true;
        else
            return false;

    }
}
