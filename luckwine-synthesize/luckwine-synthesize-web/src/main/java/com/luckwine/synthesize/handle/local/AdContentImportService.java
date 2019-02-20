package com.luckwine.synthesize.handle.local;

import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.goods.model.request.spu.SpuDetailRequest;
import com.luckwine.goods.model.response.SpuDetail;
import com.luckwine.goods.model.service.SpuService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.handle.remote.SkuDetailRemoteService;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentImportReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AdContentImportService extends SingleTemplate<AdContentImportReq, Boolean> {

    @Resource
    private AdContentMapper adContentMapper;

    @Autowired
    private SkuDetailRemoteService skuDetailRemoteService;

    @Transactional
    @Override
    protected Boolean callInner(CommonRequest<AdContentImportReq> request) throws Exception {
        AdContentImportReq adContentImportReq = request.getRequest();

        //查询商品信息
        CommonRequest<SkuDetailGetByIdsRequest> skuDetailGetByIdsRequestCommonRequest = new CommonRequest<SkuDetailGetByIdsRequest>();
        SkuDetailGetByIdsRequest skuDetailGetByIdsRequest = new SkuDetailGetByIdsRequest();
        skuDetailGetByIdsRequest.setSkuIds(adContentImportReq.getItemIdList());
        skuDetailGetByIdsRequestCommonRequest.setRequest(skuDetailGetByIdsRequest);

        CommonResponse<List<SkuDetail>> skuCommonResponse = skuDetailRemoteService.call(skuDetailGetByIdsRequestCommonRequest);

        if (skuCommonResponse == null || skuCommonResponse.getResponse() == null) {
            log.info("导入商品时, 获取商品详细信息为空");
            return false;
        }

        int weight = 1;
        int successCount = 0;
        for (SkuDetail skuDetail : skuCommonResponse.getResponse()) {
            UUID uuid = UUID.randomUUID();

            AdContent adContent = new AdContent();
            adContent.setAdContentId(uuid.toString());
            adContent.setTitle(skuDetail.getTitle());
            adContent.setItemId(skuDetail.getSkuId());
            adContent.setPrice(skuDetail.getPrice());
            adContent.setJumpUrl(String.format("/goodsDetails?productId={0}&skuId={1}", skuDetail.getSpuId(), skuDetail.getSkuId()));
            adContent.setType(3);
            adContent.setImageUrl(skuDetail.getPicPath());
            adContent.setIntroduction(skuDetail.getDetail());
            adContent.setSortWeight(weight);
            adContent.setTimeStart(adContentImportReq.getTimeStart());
            adContent.setTimeEnd(adContentImportReq.getTimeEnd());
            adContent.setStatus(0);
            adContent.setRemarks(adContentImportReq.getRemarks());
            adContent.setAdModuleId(adContentImportReq.getAdModuleId());
            adContent.setOperatingTime(new Date());
            weight++;

            successCount += adContentMapper.insert(adContent);
        }

        if (successCount == adContentImportReq.getItemIdList().size()) {
            return true;
        } else {
            return false;
        }

    }

}
