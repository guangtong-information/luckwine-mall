package com.luckwine.oss.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HowellYang
 */
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}

