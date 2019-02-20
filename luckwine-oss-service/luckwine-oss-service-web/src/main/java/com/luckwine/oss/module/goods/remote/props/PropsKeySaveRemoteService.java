package com.luckwine.oss.module.goods.remote.props;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.props.PropsKeySaveRequest;
import com.luckwine.goods.model.service.PropsService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.springframework.stereotype.Service;

@Service
public class PropsKeySaveRemoteService extends SingleRemoteTemplate<PropsKeySaveRequest, Boolean>  {

    @Reference(version = "1.0.0")
    private PropsService propsService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<PropsKeySaveRequest> request) {
        return propsService.saveKey(request);
    }
}
