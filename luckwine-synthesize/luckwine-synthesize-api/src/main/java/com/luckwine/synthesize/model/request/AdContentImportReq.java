package com.luckwine.synthesize.model.request;

import com.luckwine.parent.entitybase.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class AdContentImportReq extends BaseRequest {

    /**
     * 广告模块编码
     */
    @NotNull
    private String adModuleId;

    /**
     * 导入的商品Sku列表
     */
    @NotNull
    private List<Long> itemIdList;

    /**
     * 开始时间
     */
    @NotNull
    private Date timeStart;

    /**
     * 结束时间
     */
    @NotNull
    private Date timeEnd;

    /**
     * 导入的商品Spu列表
     */
    private String remarks;

}
