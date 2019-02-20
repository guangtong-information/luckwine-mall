package com.luckwine.customer.handle.local;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustInfoMapper;
import com.luckwine.customer.model.base.CustInfo;
import com.luckwine.customer.model.enums.ExceptionCode;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Title: 客户信息表
 * Description: 自定义本地查询,编辑等操作方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2018-9-20 10:11:39
 */
@Slf4j
@Service("custInfoQueryService")
public class CustInfoQueryService extends SingleTemplate<CustInfo, CustInfo> {
	@Resource
	private CustInfoMapper custInfoMapper;

	@Override
	protected CustInfo callInner(CommonRequest<CustInfo> request) throws Exception {
		CustInfo custInfo = request.getRequest();
		if(StringUtils.isNotEmpty(custInfo.getLoginAccount())) {
			QueryWrapper<CustInfo> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("login_account",custInfo.getLoginAccount());
			if(custInfoMapper.selectCount(queryWrapper) == 0){
				throw new CommonException(ExceptionCode.not_account.getCode(), ExceptionCode.not_account.getMessage());
			}
			queryWrapper.setEntity(custInfo);
			CustInfo result = custInfoMapper.selectOne(queryWrapper);
			return result;
		} else {
			throw new CommonException(ExceptionCode.not_login_account.getCode(), ExceptionCode.not_login_account.getMessage());
		}
	}

}
