package com.luckwine.acct.dao;

import com.luckwine.acct.model.base.AcctAbility;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AcctAbilityMapper extends Mapper<AcctAbility> {
    /**
     * 批量插入账户能力
     * @param acctCodeList
     * @param abilityCodeList
     * @return
     */
    int batchInsert(@Param("acctCodeList")List<String> acctCodeList, @Param("abilityCodeList")List<String> abilityCodeList);

    /**
     * 删除账户号
     * @param acctCodeList
     * @return
     */
    int deleteByAcctCodeList(@Param("acctCodeList")List<String> acctCodeList);



    /**
     * 查询账户号是否有入账能力
     * @param acctCode
     * @return List<Integer>
     */
    List<Integer> selectDepositByAcctCode(@Param("acctCode")String acctCode);
}