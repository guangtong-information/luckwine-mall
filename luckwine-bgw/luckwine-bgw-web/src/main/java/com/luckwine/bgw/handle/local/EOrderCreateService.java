package com.luckwine.bgw.handle.local;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.luckwine.bgw.config.KdApiProperties;
import com.luckwine.bgw.model.enums.RequestType;
import com.luckwine.bgw.model.request.EOrderCreateReq;
import com.luckwine.bgw.model.response.EOrderCreateRes;
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
public class EOrderCreateService extends SingleTemplate<EOrderCreateReq, EOrderCreateRes> {

    @Autowired
    private KdApiProperties kdApiProperties;

    @Override
    protected EOrderCreateRes callInner(CommonRequest<EOrderCreateReq> request) throws Exception {

        //TODO 第一步：持久化物流单

        //TODO 第二步：调用电子面单接口
        String requestData = JSON.toJSONString(request.getRequest());

        Map<String, String> params = new HashMap<String, String>();
        // [系统级参数] 请求内容为JSON格式
        params.put("RequestData", EcryptUtils.urlEncoder(requestData, "UTF-8"));
        // [系统级参数] 用户 ID
        params.put("EBusinessID", kdApiProperties.getEbusinessid());
        // [系统级参数] 请求接口指令
        params.put("RequestType", RequestType.EOrder.getCode());
        String dataSign= EcryptUtils.encrypt(requestData, kdApiProperties.getApikey(), "UTF-8");
        // [系统级参数] 数据内容签名
        params.put("DataSign", EcryptUtils.urlEncoder(dataSign, "UTF-8"));
        // [系统级参数] DataType=2，请求、返回数据类型均为 JSON 格式
        params.put("DataType", "2");

        String result= HttpUtils.sendPost(kdApiProperties.getEbusinessorderurl(), params);

        //TODO 第三步：同步更新状态
        log.info(result);

        return  JSON.parseObject(result, new TypeReference<EOrderCreateRes>() {}) ;
    }
}
