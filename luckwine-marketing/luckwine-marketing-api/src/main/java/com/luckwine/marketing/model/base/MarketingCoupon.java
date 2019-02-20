package com.luckwine.marketing.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "marketing_coupon")
public class MarketingCoupon extends BaseRequest implements Serializable {

    @Id
    private String couponId;

    // 券号：YYMMDDHHmmss+4位随机数-> 16位md5加密
    private String couponNum;

    // 登陆账号（关联cust_info表）
    private String loginAccount;

    // 方案id
    private String schemeId;

    // 卡券状态：未领取noget、未使用noused、已使用used、过期overdue
    private String couponStat;

    // 创建时间
    private Date createTime;

    //领取时间
    private Date getTime;

    //使用时间
    private Date useTime;

}