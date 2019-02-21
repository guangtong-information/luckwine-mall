package com.luckwine.bgw.handle.local;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.luckwine.bgw.config.KdApiProperties;
import com.luckwine.bgw.model.enums.RequestType;
import com.luckwine.bgw.model.request.EOrderRemoveReq;
import com.luckwine.bgw.model.response.EOrderRemoveRes;
import com.luckwine.bgw.util.EcryptUtils;
import com.luckwine.bgw.util.HttpUtils;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EOrderRemoveService extends SingleTemplate<EOrderRemoveReq, EOrderRemoveRes> {

    @Autowired
    private KdApiProperties kdApiProperties;

    @Override
    protected EOrderRemoveRes callInner(CommonRequest<EOrderRemoveReq> request) throws Exception {

        String requestData = JSON.toJSONString(request.getRequest());

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", EcryptUtils.urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", kdApiProperties.getEbusinessid());
        params.put("RequestType", RequestType.RemoveEOrder.getCode());
        String dataSign = EcryptUtils.encrypt(requestData, kdApiProperties.getApikey(), "UTF-8");
        params.put("DataSign", EcryptUtils.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result = HttpUtils.sendPost(kdApiProperties.getEbusinessorderurl(), params);

        log.info(result);

        return  JSON.parseObject(result, new TypeReference<EOrderRemoveRes>() {}) ;
    }
}
