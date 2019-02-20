package com.luckwine.acct.dao;

import com.luckwine.acct.model.base.AcctInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;

public interface AcctInfoMapper extends Mapper<AcctInfo> {

	/**
	 * 查询锁行
	 * @param acctCode
	 * @return
	 */
	@Select("<script>" +
			"SELECT acct_code ,avail_bal ,update_time FROM `acct_info` " +
			"WHERE acct_code=#{acctCode} " +
			"FOR UPDATE;" +
			"</script>")
	AcctInfo selectAcctInfoForUpdate(@Param("acctCode") String acctCode);

	/**
	 * 金额扣减
	 * @param acctCode
	 * @param trsAmount
	 * @return
	 */
	@Update("<script>" +
			"UPDATE `acct_info` SET avail_bal=avail_bal-#{trsAmount} " +
			"WHERE acct_code=#{acctCode} AND (avail_bal-#{trsAmount}>=0);" +
			"</script>")
	int deductingAmount(@Param("acctCode")String acctCode ,@Param("trsAmount")BigDecimal trsAmount);
}