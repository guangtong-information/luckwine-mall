package com.luckwine.customer.model.base;

import lombok.Data;
import lombok.ToString;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Table(name = "cust_info")
public class CustInfo implements Serializable {

  //@NotNull(message = "登陆账号(登录号就是手机号)")
  @Id
  private String loginAccount;

  //@NotNull(message = "密码,请加密不可逆")
  private String loginPw;

  //@NotNull(message = "支付密码,请加密不可逆")
  private String payPw;

  //@NotNull(message = "身份证")
  private String identity;

  //@NotNull(message = "注册时间")

  private Date createTime;

  //@NotNull(message = "更新时间")
  private Date updateTime;

  //@NotNull(message = "昵称")
  private String nickname;

  //@NotNull(message = "真实姓名")
  private String realname;

  //@NotNull(message = "性别:1.男 2.女 0.未知")
  private Integer sex;

  //@NotNull(message = "生日时间")
  private Date birthday;

  //@NotNull(message = "省")
  private String province;

  //@NotNull(message = "市")
  private String city;

  //@NotNull(message = "区")
  private String district;

  //@NotNull(message = "详细地址")
  private String address;

  //@NotNull(message = "常喝酒类：白酒,红酒")
  private String drinkWine;

  //@NotNull(message = "喜欢酒类品牌：牌子1,牌子2")
  private String likeWine;

  //@NotNull(message = "头像地址")
  private String headerimg;

  //@NotNull(message = "婚姻状况 1.已婚有子女 2.未婚无子女 3.恋爱中 4.单身 5.丧偶 6.离异")
  private String marry;

  //@NotNull(message = "教育程度 1.高中及以下 2.大学专科 3.大学本科 4.硕士 5.")
  private String education;

  //@NotNull(message = "从事职业")
  private String job;

  //@NotNull(message = "所属行业")
  private String industry;

  //@NotNull(message = "月均收入")
  private String income;

  //@NotNull(message = "客户等级：1.普通用户 2.普通实名")
  private Integer custLevel;

  //@NotNull(message = "客户状态：1.正常 2.冻结")
  private Integer status;



}
