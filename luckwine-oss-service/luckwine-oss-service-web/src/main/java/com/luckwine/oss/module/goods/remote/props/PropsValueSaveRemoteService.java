package com.luckwine.oss.module.goods.remote.props;

import com.alibaba.dubbo.config.annotation.Reference;
import com.luckwine.goods.model.request.props.PropsValueSaveRequest;
import com.luckwine.goods.model.service.PropsService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class PropsValueSaveRemoteService extends SingleRemoteTemplate<PropsValueSaveRequest, Boolean> {

    @DubboReference(version = "1.0.0")
    private PropsService propsService;

    @Override
    protected CommonResponse<Boolean> callRemote(CommonRequest<PropsValueSaveRequest> request) {
        return propsService.saveValue(request);
    }
}
