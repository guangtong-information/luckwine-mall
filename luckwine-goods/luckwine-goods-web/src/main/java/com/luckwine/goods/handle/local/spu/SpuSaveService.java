package com.luckwine.goods.handle.local.spu;

import com.luckwine.goods.manage.SpuManage;
import com.luckwine.goods.model.request.spu.SpuSaveRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpuSaveService extends SingleTemplate<SpuSaveRequest,Boolean> {

    @Autowired
    private SpuManage spuManage;

    @Override
    protected Boolean callInner(CommonRequest<SpuSaveRequest> request) throws Exception {
        return spuManage.save(request);
    }
}
