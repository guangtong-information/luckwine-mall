package com.luckwine.parent.entitybase.response;


import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString(callSuper = true)
public class EalsticsearchPageResponse<T>  extends CommonQueryPageResponse<T> {

    private Map<String ,List<AggregationInfo>> aggregationInfoMap;

    public static EalsticsearchPageResponse empty (ResponseCodeConstant constant) {
        return empty(constant.getResponseCode(), constant.getResponseDesc() );
    }

    public static EalsticsearchPageResponse empty () {
        return empty(ResponseCodeConstant.SUCCESS.getResponseCode(), ResponseCodeConstant.SUCCESS.getResponseDesc() );
    }

    public static EalsticsearchPageResponse empty(String code, String desc) {
        EalsticsearchPageResponse response = new EalsticsearchPageResponse<>();
        response.setCode(code);
        response.setContent(desc);
        response.setResponse(new ArrayList());
        response.setAggregationInfoMap(new HashMap<>(0));
        response.setTotalCount(0L);
        response.setTotalPage(0);
        return response;
    }

}
