package com.luckwine.bgw.model.base;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Table(name = "express_user")
public class ExpressUser extends BaseRequest {

    @Id
    private String id;

    //公司
    private String company;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "省份不能为空")
    private String provinceName;

    @NotBlank(message = "市不能为空")
    private String cityName;

    @NotBlank(message = "区/县不能为空")
    private String expAreaName;

    @NotBlank(message = "详细地址不能为空")
    private String address;

    //邮编
    private String postCode;

    @NotBlank(message = "类型(0：发件人，1：收件人)不能为空")
    private String type;

    @NotBlank(message = "子单号不能为空")
    private String subOrderCode;

    @NotBlank(message = "主单号不能为空")
    private String mainOrderCode;

    private Date createDate;

    private Date updateDate;
}