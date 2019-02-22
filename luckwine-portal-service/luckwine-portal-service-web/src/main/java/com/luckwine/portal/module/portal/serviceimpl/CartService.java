package com.luckwine.portal.module.portal.serviceimpl;

import com.luckwine.portal.goods.CartBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CartService {
    private Map<Long, CartBase> cartBaseList ;

    public List list() {
        return Arrays.asList(cartBaseList.values().toArray());
    }

    @Cacheable(value = "portal", key = "'cart::'.concat(#key.toString())")
    public List findInfoBykey(String key) {
        cartBaseList = new HashMap<>();

        //todo: get CartData by mysql
        return Arrays.asList(cartBaseList.values().toArray());
    }

    @CachePut(value = "portal", key = "'cart::'.concat(#key.toString())")
    public List update(CartBase cartBase, String key) {
        //todo: update CartData by mysql
        cartBaseList.put(cartBase.getProductId(), cartBase);

        return Arrays.asList(cartBaseList.values().toArray());
    }

    @CacheEvict(value = "portal", key = "'cart::'.concat(#key.toString())")
    public List remove(CartBase cartBase, String key) {
        //todo: remove CartData by mysql
        cartBaseList.remove(cartBase.getProductId(), cartBase);

        return Arrays.asList(cartBaseList.values().toArray());
    }

}
