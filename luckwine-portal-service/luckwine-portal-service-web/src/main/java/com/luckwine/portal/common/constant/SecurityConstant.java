package com.luckwine.portal.common.constant;

/**
 * @author HowellYang
 * 加密因子
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer_";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "luckwine-portal-service";

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";
}
