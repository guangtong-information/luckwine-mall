package com.luckwine.portal.customer.service;

import com.luckwine.customer.model.base.CustInfo;

public interface CustomerInfoService {
    CustInfo findByLoginAccount(String loginAccount);
    void delCache(String loginAccount);
}
