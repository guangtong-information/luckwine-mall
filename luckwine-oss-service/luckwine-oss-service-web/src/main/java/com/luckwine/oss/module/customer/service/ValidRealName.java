package com.luckwine.oss.module.customer.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class ValidRealName {

	public boolean isReal(String cardno,String name){
		if("123456".equals(cardno) && "aa".equals(name)){
			return true;
		}

		return false;
	}
}
