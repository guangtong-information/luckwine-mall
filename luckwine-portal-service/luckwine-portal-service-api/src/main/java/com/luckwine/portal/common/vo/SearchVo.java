package com.luckwine.portal.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HowellYang
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;
}
