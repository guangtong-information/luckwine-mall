package com.luckwine.portal.module.portal.serviceimpl;

import com.luckwine.portal.common.constant.SecurityConstant;
import com.luckwine.portal.module.portal.service.GetAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class GetAuthenticationServiceImpl implements GetAuthenticationService {

    public String getUsernameByToken(HttpServletRequest request){
        String token = "";
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies().clone();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SecurityConstant.HEADER)) {
                    token = cookie.getValue();
                }
            }
        }
        // 解析token
        Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                        .parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
                        .getBody();
        //获取用户名
        String username = claims.getSubject();

        return username;
    }

}

