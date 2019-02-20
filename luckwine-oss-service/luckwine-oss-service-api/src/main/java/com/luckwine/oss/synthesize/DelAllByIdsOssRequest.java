package com.luckwine.oss.synthesize;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public class DelAllByIdsOssRequest extends BaseRequest {
    String[] list;
}
