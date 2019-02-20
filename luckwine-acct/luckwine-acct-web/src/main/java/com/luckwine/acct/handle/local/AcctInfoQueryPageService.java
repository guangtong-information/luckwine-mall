package com.luckwine.acct.handle.local;

import com.luckwine.acct.dao.AcctInfoMapper;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户查询列表
 */
@Service
public class AcctInfoQueryPageService extends QueryPageTemplate<AcctInfoPageRequest,  AcctInfo> {

    @Resource
    private AcctInfoMapper acctInfoMapper;

    @Override
    protected List<AcctInfo> callInner(CommonQueryPageRequest<AcctInfoPageRequest> request) {
        //========= 搜索条件 =========
        Example example = new Example(AcctInfo.class);
        Example.Criteria criteria = example.createCriteria();
        //账户号
        if (request.getRequest().getAcctCode() != null)
            criteria.andEqualTo("acctCode", request.getRequest().getAcctCode());
        //账户名称
        if (request.getRequest().getAcctName() != null)
            criteria.andLike("acctName", "%" + request.getRequest().getAcctName() + "%");
        //客户登录账号
        if (request.getRequest().getLoginAccount() != null)
            criteria.andLike("loginAccount", "%" + request.getRequest().getLoginAccount() + "%");
        //账户状态
        if (request.getRequest().getStat() != null)
            criteria.andEqualTo("stat", request.getRequest().getStat());
        //账户能力
        if (request.getRequest().getAbilityCode() != null)
            criteria.andCondition("`acct_code` IN (SELECT s.`acct_code` FROM `acct_ability` s WHERE s.`ability_code`='" + request.getRequest().getAbilityCode() + "')");
        //开户时间
        if (request.getRequest().getCreateTimeStart() != null && request.getRequest().getCreateTimeEnd() != null)
            criteria.andBetween("createTime", request.getRequest().getCreateTimeStart(), request.getRequest().getCreateTimeEnd());
        //========= 查询数据库 =========
        return acctInfoMapper.selectByExample(example);
    }



}
