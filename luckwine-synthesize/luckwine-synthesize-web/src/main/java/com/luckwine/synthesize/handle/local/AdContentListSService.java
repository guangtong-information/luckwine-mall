package com.luckwine.synthesize.handle.local;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.dao.AdContentMapper;
import com.luckwine.synthesize.model.base.AdContent;
import com.luckwine.synthesize.model.request.AdContentSReq;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdContentListSService extends SingleTemplate<AdContentSReq, Map<String, List<AdContent>>> {

    @Resource
    private AdContentMapper adContentMapper;

    @Override
    protected Map<String, List<AdContent>> callInner(CommonRequest<AdContentSReq> request) throws Exception {
        String[] adModuleIds = request.getRequest().getAdModuleIds();
        String pageId = request.getRequest().getPageId();
        List<AdContent> list = new ArrayList<>();

        if (StringUtils.isEmpty(pageId)) {
            Example example = new Example(AdContent.class);
            example.createCriteria()
                    .orEqualTo("status", request.getRequest().getAdContentEnum().getCode());
            for (String item : adModuleIds) {
                example.or().orEqualTo(item);
            }
            list = adContentMapper.selectByExample(example);
        } else {
            list = adContentMapper.selectAdContentByPageId(pageId, Integer.valueOf(request.getRequest().getAdContentEnum().getCode()));
        }

        Map<String, List<AdContent>> map = new HashMap<>();
        for (AdContent adContent : list) {
            List<AdContent> itemList = map.get(adContent.getAdModuleId());
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
            itemList.add(adContent);
            map.put(adContent.getAdModuleId(), itemList);
        }
        return map;
    }

}
