package com.luckwine.customer.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HowellYang
 */
@Data
public class SearchCreateTime implements Serializable {

    private String createStartDate;

    private String createEndDate;
}
