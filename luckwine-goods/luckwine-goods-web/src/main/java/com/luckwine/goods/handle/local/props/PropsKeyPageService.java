package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.request.props.PropsKeyPageRequest;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Title: PropsKey表
 * Description: 属性Key分页查询方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-3 15:52
 * invoke PropsService.pageKey({"class":"com.luckwine.parent.entitybase.request.CommonQueryPageRequest","pageNo":"1","pageSize":"10","request":{"class":"com.luckwine.goods.model.request.props.PropsKeyPageRequest","key":"产区"}})
 */
@Service
public class PropsKeyPageService extends QueryPageTemplate<PropsKeyPageRequest, PropsKey> {

    @Autowired
    private PropsKeyMapper propsKeyMapper;

    @Override
    protected List<PropsKey> callInner(CommonQueryPageRequest<PropsKeyPageRequest> request) throws Exception {
        Example example = new Example(PropsKey.class);
        if (StringUtils.isNotBlank(request.getRequest().getKey())) {
            example.createCriteria().andLike( "key","%" + request.getRequest().getKey() + "%");
        }
        return propsKeyMapper.selectByExample(example);
    }
}
