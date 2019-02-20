package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsValueMapper;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.request.props.PropsValuePageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * Title: PropsValue表
 * Description: 属性Value分页查询方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-4
 */
@Service
public class PropsValuePageService extends QueryPageTemplate<PropsValuePageRequest, PropsValue> {

    @Autowired
    private PropsValueMapper propsValueMapper;

    @Override
    protected List<PropsValue> callInner(CommonQueryPageRequest<PropsValuePageRequest> request) throws Exception {
        Example example = new Example(PropsValue.class);
        if (StringUtils.isNotBlank(request.getRequest().getKeyId()+"")) {
            example.createCriteria().andEqualTo( "keyId",request.getRequest().getKeyId() );
        }
        if (StringUtils.isNotBlank(request.getRequest().getValue())) {
            example.createCriteria().andLike( "value","%" + request.getRequest().getValue() + "%");
        }
        return propsValueMapper.selectByExample(example);
    }
}
