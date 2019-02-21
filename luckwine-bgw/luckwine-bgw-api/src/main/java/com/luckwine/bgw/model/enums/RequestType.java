package com.luckwine.bgw.model.enums;

import lombok.Getter;

/**
 *  快递鸟请求接口类型
 */
public enum RequestType {

    CreateOrder("1001","预约取件"),
    TrackQuery("1002", "即时查询"),
    RemoveOrder("1004","预约取件取消"),
    EOrder("1007", "电子面单"),
    RemoveEOrder("1147", "电子面单取消"),
    ExpRecommend("2006","智选物流");

    @Getter
    private final String code;

    @Getter
    private final String desc;

    RequestType(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
