package com.luckwine.trade.integration.validator;

import java.util.ArrayList;
import java.util.List;


public class ValidationException extends Exception {
    private List<String> errors = null;

    public ValidationException(String msg) {
        super(msg);
        errors = new ArrayList<String>();
        errors.add(msg);
    }

    public ValidationException(List<String> msgList) {
        errors = msgList;
    }

    @Override
    public String getMessage() {
        return listToString(errors);
    }

    private String listToString(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String str : list) {
            sb.append(str);
            sb.append("\r\n");
        }
        return sb.toString().trim();
    }
}
