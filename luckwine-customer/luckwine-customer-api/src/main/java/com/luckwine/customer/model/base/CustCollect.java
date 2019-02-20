package com.luckwine.customer.model.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "cust_collect")
public class CustCollect implements Serializable {

 //  "登陆账号(登录号就是手机号)")
     @Id
     private String loginAccount;
     // "null")
     @Id
     private String goodsId;
     // "创建时间")
     private Date createTime;

}
