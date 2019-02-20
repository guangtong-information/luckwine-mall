package com.luckwine.synthesize.model.response;

import com.luckwine.parent.entitybase.response.BaseResponse;
import com.luckwine.synthesize.model.base.AdContent;

import java.util.List;

public class AdContentDetail extends BaseResponse {
    private AdContent adContent;

    private String categoryName;

    private String brandName;

    private List<AdContent> adContents;
}
