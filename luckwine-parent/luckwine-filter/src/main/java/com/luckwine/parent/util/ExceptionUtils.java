package com.luckwine.parent.util;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExceptionUtils {

    public static String getMsg(ConstraintViolationException cvException) {
        Set<ConstraintViolation<?>> set =  cvException.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = set.iterator();
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cv = iterator.next();
            list.add(cv.getPropertyPath() + ":" + cv.getMessage());
        }
        return list.toString();
    }
}
