package com.luckwine.marketing.handle.local;

import cn.hutool.crypto.SecureUtil;
import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.enums.MarketingCouponEnum;
import com.luckwine.marketing.model.request.enums.MarketingSchemeEnum;
import com.luckwine.marketing.model.request.scheme.GenerateCouponsReq;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class GenerateCouponsService extends SingleTemplate<GenerateCouponsReq, Boolean> {

    @Resource
    private MarketingCouponMapper marketingCouponMapper;

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;

    @Override
    @Transactional
    protected Boolean callInner(CommonRequest<GenerateCouponsReq> request) throws Exception {

        //获取要生产的优惠券数量
        MarketingScheme marketingScheme = marketingSchemeMapper.selectByPrimaryKey(request.getRequest().getSchemeId());

        //判断当前营销方案的状态
        if (!marketingScheme.getSchemeStat().equals(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode())) {
            return false;
        }

        Integer total = marketingScheme.getDiscountTotal();

        if (null == total) {
            return true;
        }

        int successAmount = 0;
        for (int i = 0; i < total; i++) {
            UUID uuid = UUID.randomUUID();
            MarketingCoupon marketingCoupon = new MarketingCoupon();
            marketingCoupon.setCouponId(uuid.toString());
            marketingCoupon.setCouponNum(generaCouponNumber());
            marketingCoupon.setLoginAccount(null);
            marketingCoupon.setSchemeId(request.getRequest().getSchemeId());
            marketingCoupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_NOGET.getCode());
            marketingCoupon.setCreateTime(new Date());
            marketingCoupon.setGetTime(null);
            marketingCoupon.setUseTime(null);
            successAmount += marketingCouponMapper.insert(marketingCoupon);
        }

        if (successAmount == total) {
            //更新营销方案表
            marketingScheme.setGenCoupon(true);
            marketingSchemeMapper.updateByPrimaryKey(marketingScheme);
            return true;
        } else {
            return false;
        }
    }

    private String generaCouponNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        LocalDateTime localDateTime = LocalDateTime.now();
        String formatDate = localDateTime.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        String couponNum = formatDate + randomNumber;
        couponNum = SecureUtil.md5(couponNum).substring(8, 24);
        return couponNum;
    }

}
