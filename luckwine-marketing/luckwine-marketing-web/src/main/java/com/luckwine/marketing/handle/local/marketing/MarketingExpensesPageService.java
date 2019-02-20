package com.luckwine.marketing.handle.local.marketing;

import com.luckwine.marketing.dao.MarketingExpensesMapper;
import com.luckwine.marketing.model.base.MarketingExpenses;
import com.luckwine.marketing.model.base.MarketingScheme;
import com.luckwine.marketing.model.expenses.MarketingExpensesReq;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MarketingExpensesPageService extends QueryPageTemplate<MarketingExpensesReq, MarketingExpenses> {

    @Resource
    private MarketingExpensesMapper marketingExpensesMapper;

    @Override
    protected List<MarketingExpenses> callInner(CommonQueryPageRequest<MarketingExpensesReq> request) throws Exception {
        Example example = new Example(MarketingExpenses.class);
        Example.Criteria criteria = example.createCriteria();
        //登录账号
        if (StringUtils.isNotBlank(request.getRequest().getUserAccount())) {
            criteria.andLike("userAccount", "%" + request.getRequest().getUserAccount() + "%");
        }
        //主单号
        if (StringUtils.isNotBlank(request.getRequest().getExtTrsSeq())) {
            criteria.andLike("extTrsSeq", "%" + request.getRequest().getExtTrsSeq() + "%");
        }
        //营销交易流水号
        if (StringUtils.isNotBlank(request.getRequest().getMarketOrderno())) {
            criteria.andLike("marketOrderno", "%" + request.getRequest().getMarketOrderno() + "%");
        }
        //券号
        if (StringUtils.isNotBlank(request.getRequest().getCouponNum())) {
            criteria.andLike("couponNum", "%" + request.getRequest().getCouponNum() + "%");
        }
        //资金流水号
        if (StringUtils.isNotBlank(request.getRequest().getRequestSeq())) {
            criteria.andLike("requestSeq", "%" + request.getRequest().getRequestSeq() + "%");
        }
        //方案ID
        if (StringUtils.isNotBlank(request.getRequest().getSchemeId())) {
            criteria.andLike("schemeId", "%" + request.getRequest().getSchemeId() + "%");
        }
        //创建时间
        if (request.getRequest().getCreateStartDate() != null && request.getRequest().getCreateEndDate() != null)
            criteria.andBetween("createTime", request.getRequest().getCreateStartDate(), request.getRequest().getCreateEndDate());


        return marketingExpensesMapper.selectByExample(example);


//        return marketingExpensesMapper.selectAll();
    }
}
