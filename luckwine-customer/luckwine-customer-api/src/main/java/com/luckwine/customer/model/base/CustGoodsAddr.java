package com.luckwine.customer.model.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@ToString
@Table(name = "cust_goods_addr")
public class CustGoodsAddr implements Serializable {


  @Id
  private String goodsAddrId;
 //@NotNull(message = "登陆账号(登录号就是手机号)")
  private String loginAccount;
 //@NotNull(message = "收货人")
  private String consignee;
 //@NotNull(message = "省")
  private String province;
 // "市")
  private String city;
 // "区")
  private String district;
 // "详细地址")
  private String address;
 // "邮政编码")
  private String postcode;
 // "手机号码")
  private String moblie;
 // "固定电话")
  private String telephone;

}
