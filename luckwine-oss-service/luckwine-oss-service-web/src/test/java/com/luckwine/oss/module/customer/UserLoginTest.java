package com.luckwine.oss.module.customer;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.UploadPartRequest;
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
