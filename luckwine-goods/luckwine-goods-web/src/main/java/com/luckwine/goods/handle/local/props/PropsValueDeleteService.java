package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.dao.PropsValueMapper;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.request.props.PropsValueDeleteRequest;
import com.luckwine.goods.model.request.props.PropsValueModifyRequest;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Title: PropsValue表
 * Description: 属性Value删除方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-4
 */
@Service
public class PropsValueDeleteService extends SingleTemplate<PropsValueDeleteRequest, Boolean> {

    @Autowired
    private PropsKeyMapper propsKeyMapper;


    @Autowired
    private PropsValueMapper propsValueMapper;

    @Override
    protected Boolean callInner(CommonRequest<PropsValueDeleteRequest> request) throws Exception {
        if ( null == request.getRequest() || null == request.getRequest().getId() ) {
            throw new ParamErrorException("Id错误");
        }

        int count = propsValueMapper.deleteByPrimaryKey( request.getRequest().getId() );
        if( count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
