package com.luckwine.marketing.handle.local.marketing;

import com.luckwine.marketing.dao.MarketingCouponMapper;
import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingCoupon;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.DataBaseTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MarketingSchemeUpdateService extends DataBaseTemplate<MarketingScheme,Boolean> {

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;
    @Resource
    private MarketingCouponMapper marketingCouponMapper;

    @Override
    protected Boolean callInner(CommonRequest<MarketingScheme> request) throws Exception {
        MarketingScheme marketingScheme = request.getRequest();
        Example example = new Example(MarketingCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(marketingScheme.getSchemeId()))
            criteria.andEqualTo("schemeId",marketingScheme.getSchemeId());
        List<MarketingCoupon> list = marketingCouponMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list))
            throw new CommonException(ResponseCodeConstant.DUBBO_BIZ_EXCEPTION.getResponseCode(),"生成优惠券后，优惠方案不能再修改！");
        int row = marketingSchemeMapper.updateByPrimaryKeySelective(marketingScheme);
        if(row>0)
            return true;
        else
            return false;
    }
}
