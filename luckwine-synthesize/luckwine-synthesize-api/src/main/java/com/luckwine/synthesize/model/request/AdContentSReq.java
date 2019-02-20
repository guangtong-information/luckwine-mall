package com.luckwine.synthesize.model.request;

import com.luckwine.parent.entitybase.response.BaseResponse;
import com.luckwine.synthesize.model.request.enums.AdContentEnum;
import lombok.Data;

@Data
public class AdContentSReq extends BaseResponse {
    // 所属页面编码
    private String pageId;

    // 广告模块编码
    private String[] adModuleIds;

    private AdContentEnum adContentEnum;
}
