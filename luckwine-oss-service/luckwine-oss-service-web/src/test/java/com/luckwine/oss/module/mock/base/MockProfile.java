package com.luckwine.oss.module.mock.base;

import lombok.Getter;


public enum MockProfile {
    DUBBO("dubbo"),
    MOCK("default"),
    LOCAL("local");

    @Getter
    private String value;

    MockProfile(String value) {
        this.value = value;
    }
}
