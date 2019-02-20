package com.luckwine.trade.model.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString(callSuper = true)
public class ShopCartSumRes implements Serializable {

    private int number;

}
