package com.luckwine.parent.util;

import com.luckwine.parent.entitybase.enums.OperLevel;
import com.luckwine.parent.entitybase.request.CommonRequest;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class RequestLogUtils {

    private static final String TRACE_ID = "traceId";
    private static final String OPER_LEVEL = "operLevel";


    public static void initLog(CommonRequest request) {
        MDC.put(TRACE_ID, getCurTraceId(request));
        MDC.put(OPER_LEVEL, getCurOperLevel(request));
    }

    public static void initLog() {
        MDC.put(TRACE_ID, getCurTraceId());
        MDC.put(OPER_LEVEL, getCurOperLevel());
    }

    public static String getCurTraceId(CommonRequest request) {
        if (request != null && !StringUtils.isEmpty(request.getTraceId())) {
            return request.getTraceId();
        } else {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    public static String getCurTraceId() {
        if (MDC.get(TRACE_ID) == null) {
            return UUID.randomUUID().toString().replace("-", "");
        } else {
            return MDC.get(TRACE_ID);
        }
    }

    public static void logClear() {
        MDC.clear();
    }

    public static String getCurOperLevel(){
        return MDC.get(OPER_LEVEL) == null ? OperLevel.DEFAULT.getCode() : MDC.get(OPER_LEVEL);
    }

    public static String getCurOperLevel(CommonRequest request){
        if (StringUtils.isEmpty(request.getOperLevel())) {
            return OperLevel.DEFAULT.getCode();
        } else {
            return request.getOperLevel();
        }
    }


}
