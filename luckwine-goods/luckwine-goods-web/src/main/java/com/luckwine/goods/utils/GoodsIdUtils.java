package com.luckwine.goods.utils;


import org.springframework.stereotype.Component;

@Component
public class GoodsIdUtils {

    private Sequence sequence = new Sequence();

    public String generateId() {
       return String.valueOf(sequence.nextId());
    }

}
