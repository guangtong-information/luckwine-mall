package com.luckwine.parent.entitybase.exception;

import lombok.Data;
import lombok.ToString;

/**
 * Created by winlone* Date: 2016-8-8.
 */
@Data
@ToString(callSuper = true)
public class CommonException extends RuntimeException {

    private String code;


    public CommonException(String code, String message) {
        super(message);
        this.code = code;
    }

}
