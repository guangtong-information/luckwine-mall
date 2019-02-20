package com.luckwine.goods.model.response;

import com.luckwine.parent.entitybase.response.BaseResponse;
import lombok.Data;

@Data
public class Props extends BaseResponse {

    private String key;

    private String keyStr;

    private String value;

    private String valueStr;

    public String getItem() {
        return key + ":" + value;
    }

    public String getItemStr() {
        return keyStr + ":" + valueStr;
    }
}
