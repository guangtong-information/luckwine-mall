package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.dao.PropsValueMapper;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.request.props.PropsValueSaveRequest;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropsValueSaveService extends SingleTemplate<PropsValueSaveRequest, Boolean> {

    @Autowired
    private PropsKeyMapper propsKeyMapper;


    @Autowired
    private PropsValueMapper propsValueMapper;

    @Override

    protected Boolean callInner(CommonRequest<PropsValueSaveRequest> request) throws Exception {
        PropsKey propsKey =  propsKeyMapper.selectByPrimaryKey(request.getRequest().getKeyId());
        if (propsKey == null) {
            throw new ParamErrorException("keyId错误");
        }

        PropsValue propsValue = new PropsValue();
        propsValue.setKeyId(request.getRequest().getKeyId());
        propsValue.setValue(request.getRequest().getValue());
        int count = propsValueMapper.insertSelective(propsValue);
        if( count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
