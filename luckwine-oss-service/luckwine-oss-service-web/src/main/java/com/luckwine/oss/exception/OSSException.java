package com.luckwine.oss.exception;

import lombok.Data;

/**
 * @author HowellYang
 */
@Data
public class OSSException extends RuntimeException {

    private String msg;

    public OSSException(String msg){
        super(msg);
        this.msg = msg;
    }
}
