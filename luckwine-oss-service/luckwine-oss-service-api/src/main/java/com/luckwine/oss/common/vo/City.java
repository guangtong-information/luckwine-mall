package com.luckwine.oss.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HowellYang
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
