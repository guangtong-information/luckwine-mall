package com.luckwine.parent.entitybase.exception;

import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ParamErrorException extends CommonException {

    public ParamErrorException(String message) {
        super(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode(), message);
    }
}
