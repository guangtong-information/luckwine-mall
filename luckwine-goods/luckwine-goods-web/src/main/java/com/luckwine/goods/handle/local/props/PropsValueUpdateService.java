package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.dao.PropsValueMapper;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.request.props.PropsValueModifyRequest;
import com.luckwine.goods.model.request.props.PropsValueSaveRequest;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Title: PropsValue表
 * Description: 属性Value修改方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-4
 */
@Service
public class PropsValueUpdateService extends SingleTemplate<PropsValueModifyRequest, Boolean> {

    @Autowired
    private PropsKeyMapper propsKeyMapper;


    @Autowired
    private PropsValueMapper propsValueMapper;

    @Override
    protected Boolean callInner(CommonRequest<PropsValueModifyRequest> request) throws Exception {
        PropsKey propsKey =  propsKeyMapper.selectByPrimaryKey(request.getRequest().getKeyId());
        if (propsKey == null) {
            throw new ParamErrorException("keyId错误");
        }

        PropsValue propsValue = new PropsValue();
        propsValue.setId(request.getRequest().getId());
        propsValue.setKeyId(request.getRequest().getKeyId());
        propsValue.setValue(request.getRequest().getValue());
        propsValue.setUpdateTime( new Date() );
        int count = propsValueMapper.updateByPrimaryKeySelective(propsValue);
        if( count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
