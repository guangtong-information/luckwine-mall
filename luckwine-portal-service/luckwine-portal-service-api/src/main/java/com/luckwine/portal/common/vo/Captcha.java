package com.luckwine.portal.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author HowellYang
 */
@Data
public class Captcha implements Serializable{

    @ApiModelProperty(value = "验证码id")
    private String captchaId;

    @ApiModelProperty(value = "验证码")
    private String code;
}
