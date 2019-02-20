package com.luckwine.parent.tools.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBetween {

    public Date startDate(String datetime) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(datetime + "T00:00:00");
    }

    public Date endDate(String datetime) throws ParseException {
       return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(datetime + "T59:59:59");
    }
}
