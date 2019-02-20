package com.luckwine.marketing.handle.local.marketing;

import com.luckwine.marketing.dao.MarketingSchemeMapper;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.request.scheme.CouponCenterPageListReq;
import com.luckwine.marketing.model.response.CouponCenterPageListResp;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MarketingQuerySchemePageService extends QueryPageTemplate<CouponCenterPageListReq, MarketingScheme> {

    @Resource
    private MarketingSchemeMapper marketingSchemeMapper;


    @Override
    protected List<MarketingScheme> callInner(CommonQueryPageRequest<CouponCenterPageListReq> request) throws Exception {
        Example example = new Example(MarketingScheme.class);
        Example.Criteria criteria = example.createCriteria();
        //方案名称
        if (StringUtils.isNotBlank(request.getRequest().getSchemeName())) {
            criteria.andLike("schemeName", "%" + request.getRequest().getSchemeName() + "%");
        }
        //状态
        if (StringUtils.isNotBlank(request.getRequest().getSchemeType())) {
            criteria.andEqualTo("schemeType", request.getRequest().getSchemeType());
        }
        //方案对象
        if (StringUtils.isNotBlank(request.getRequest().getSchemeObj())) {
            criteria.andEqualTo("schemeObj", request.getRequest().getSchemeObj());
        }
        //方案状态
        if (StringUtils.isNotBlank(request.getRequest().getSchemeStat())) {
            criteria.andEqualTo("schemeStat", request.getRequest().getSchemeStat());
        }
        //是否上架
        if (request.getRequest().getOnlineCouponCenter() != null) {
            criteria.andEqualTo("onlineCouponCenter", request.getRequest().getOnlineCouponCenter());
        }
        //是否已经生成优惠券：0.未生成 1.已生成
        if (request.getRequest().getGenCouponInt() != null ) {
            criteria.andEqualTo("genCoupon", request.getRequest().getGenCouponInt());
        }
//        if (request.getRequest().getGenCouponInt() != null && request.getRequest().getGenCouponInt() == 0) {
//            criteria.andEqualTo("genCoupon", false);
//        }

        //创建时间
        if (request.getRequest().getCreateStartDate() != null && request.getRequest().getCreateEndDate() != null)
            criteria.andBetween("createTime", request.getRequest().getCreateStartDate(), request.getRequest().getCreateEndDate());


        return marketingSchemeMapper.selectByExample(example);
    }
}
