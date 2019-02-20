package com.luckwine.oss.module.acct.service;

public class MethodTest {
	//主方法
	public String gg(boolean flag){
		System.err.println("coming.........");
		String d = g(flag);//子方法
		h();
		System.err.println("result data is "+d);
		return d;
	}

	public String g(boolean flag){
		System.err.println("coming.........g");
		if(flag){
			throw new IllegalAccessError();//flag为true时抛异常
		}
		return "d";
	}

	public void h(){
		System.err.println("coming.........h");
	}
}
