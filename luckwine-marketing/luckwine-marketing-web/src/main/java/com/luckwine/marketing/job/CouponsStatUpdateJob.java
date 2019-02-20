package com.luckwine.marketing.job;

import cn.hutool.core.date.DateUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.enums.MarketingCouponEnum;
import com.luckwine.marketing.model.request.enums.MarketingSchemeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class CouponsStatUpdateJob implements SimpleJob {

    @Autowired
    private MarketingCouponMapper marketingCouponMapper;

    @Autowired
    private MarketingSchemeMapper marketingSchemeMapper;

    @Override
    public void execute(ShardingContext shardingContext) {
        //获取当前时间
        Date date = new Date();

        //设置更新类
        MarketingScheme updateScheme = new MarketingScheme();
        MarketingCoupon updateCoupon = new MarketingCoupon();

        //设置查询条件
        Example example = new Example(MarketingScheme.class);
        Example.Criteria criteria = example.createCriteria();

        //失效时间小于等于当前时间, 即方案失效
        criteria.andLessThanOrEqualTo("effectiveEndtime", date);
        updateScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_INVALID.getCode());
        marketingSchemeMapper.updateByExampleSelective(updateScheme, example);

        //如果生效时间大于当前时间, 则方案未生效, 设置为停用
        criteria = example.createCriteria();
        criteria.andGreaterThan("effectiveStarttime", date);
        updateScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_DISABLE.getCode());
        marketingSchemeMapper.updateByExampleSelective(updateScheme, example);

        //在启用时间段内, 设置为启动
        criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo("effectiveStarttime", date);
        criteria.andLessThan("effectiveEndtime", date);
        updateScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode());
        marketingSchemeMapper.updateByExampleSelective(updateScheme, example);

        //更新方案失效对应的优惠券
        example = new Example(MarketingCoupon.class);
        criteria = example.createCriteria();
        criteria.andCondition("scheme_id IN (SELECT scheme_id FROM marketing_scheme WHERE '" + DateUtil.now() + "' >= effective_endtime AND scheme_stat = '" + MarketingSchemeEnum.SCHEME_STAT_INVALID.getCode() + "')");
        updateCoupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_OVERDUE.getCode());
        marketingCouponMapper.updateByExampleSelective(updateCoupon, example);

    }

/*
    private void discardCode() {
        List<MarketingScheme> list = marketingSchemeMapper.selectAll();

        if (list == null || list.size() == 0) {
            return;
        }

        //优惠券只需要管有没有过期即可
        //需要更新的优惠券
        List<MarketingCoupon> overdueCouponList = new ArrayList<>();

        //获取当前时间
        Long nowDateTime = System.currentTimeMillis();
        for (MarketingScheme marketingScheme : list) {
            //获取营销方案的失效时间, 并根据失效时间, 修改方案状态
            if(nowDateTime >= marketingScheme.getEffectiveEndtime().getTime()) {
                //当前时间大于失效时间, 即方案失效
                if (marketingScheme.getSchemeStat().equals(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode())) {
                    marketingScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_INVALID.getCode());
                    //当方案失效时, 设置其对应的优惠券失效
                    Example example = new Example(MarketingCoupon.class);
                    example.createCriteria().andEqualTo("schemeId", marketingScheme.getSchemeId());
                    overdueCouponList.addAll(marketingCouponMapper.selectByExample(example));

                    marketingSchemeMapper.updateByPrimaryKeySelective(marketingScheme);
                    log.info("{}方案已经更新, 状态为:{}", marketingScheme.getSchemeName(), MarketingSchemeEnum.SCHEME_STAT_INVALID.getName());
                }
            } else if (nowDateTime < marketingScheme.getEffectiveStarttime().getTime()) {
                //获取营销方案的生效时间, 并根据生效时间, 修改方案状态
                //如果当前时间小于生效, 则方案未生效, 设置为停用
                if(marketingScheme.getSchemeStat().equals(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode())){
                    marketingScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_DISABLE.getCode());

                    marketingSchemeMapper.updateByPrimaryKeySelective(marketingScheme);
                    log.info("{}方案已经更新, 状态为:{}", marketingScheme.getSchemeName(), MarketingSchemeEnum.SCHEME_STAT_INVALID.getName());
                }
            } else {
                if (!marketingScheme.getSchemeStat().equals(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode())){
                    //在启用时间段内, 设置为启动
                    marketingScheme.setSchemeStat(MarketingSchemeEnum.SCHEME_STAT_ENABLED.getCode());

                    marketingSchemeMapper.updateByPrimaryKeySelective(marketingScheme);
                    log.info("{}方案已经更新, 状态为:{}", marketingScheme.getSchemeName(), MarketingSchemeEnum.SCHEME_STAT_INVALID.getName());
                }
            }
        }

        for (MarketingCoupon marketingCoupon : overdueCouponList) {
            marketingCoupon.setCouponStat(MarketingCouponEnum.COUPONSTAT_OVERDUE.getCode());
            marketingCouponMapper.updateByPrimaryKeySelective(marketingCoupon);
            log.info("{}优惠券已经更新, 状态为:{}", marketingCoupon.getCouponNum() , MarketingCouponEnum.COUPONSTAT_OVERDUE.getName());
        }
    }
*/

}
