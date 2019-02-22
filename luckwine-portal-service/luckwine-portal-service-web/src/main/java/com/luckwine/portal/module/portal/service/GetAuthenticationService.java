package com.luckwine.portal.module.portal.service;

import javax.servlet.http.HttpServletRequest;

public interface GetAuthenticationService {
    String getUsernameByToken(HttpServletRequest request);
}
