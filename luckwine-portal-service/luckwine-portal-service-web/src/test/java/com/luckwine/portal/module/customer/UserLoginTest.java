package com.luckwine.portal.module.customer;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserLoginTest {
    @Test
    public void  testLogin(){
        String encryptPass= new BCryptPasswordEncoder().encode("123456");
        System.out.println(encryptPass);

        if(!new BCryptPasswordEncoder().matches("123456", encryptPass)){
            System.out.println("密码不正确");
        } else {
            System.out.println("密码正确");
        }

    }
}
