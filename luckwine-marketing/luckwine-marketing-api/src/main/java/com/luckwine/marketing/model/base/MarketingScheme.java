package com.luckwine.marketing.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "marketing_scheme")
public class MarketingScheme extends BaseRequest implements Serializable {

    // 方案id
    @Id
    private String schemeId;

    // 方案名称
    private String schemeName;

    // 方案分类：卡券-coupon 优惠-discount
    private String schemeType;

    // 方案优惠对象：0.全场商品 1.指定商品 2.商品分类
    private String schemeObj;

    // 方案生效开始时间
    private Date effectiveStarttime;

    // 方案生效结束时间
    private Date effectiveEndtime;

    // 方案状态：启用enabled 停用disable 已失效invalid
    private String schemeStat;

    // 优惠券总量：卡券才有用
    private Integer discountTotal;

    // 限领张数：卡券才有用
    private Integer getLimit;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 后台OSS创建人id
    private String submitUserId;

    // 后台OSS创建人名称
    private String submitUsername;

    // 满足使用的规则：满额full_amount，满减full_discount
    private String useRule;

    // 满足数值：满多少元
    private BigDecimal useAmount;

    // 优惠数值
    private BigDecimal discountAmount;

    // 优惠单位：元rmb, 折扣discount
    private String discountUnit;

    // 是否已经生成优惠券：0.未生成 1.已生成
    private Boolean genCoupon;

    //是否上架领券中心:0.未上架 1.上架
    private Boolean onlineCouponCenter;

}