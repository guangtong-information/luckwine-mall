package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.request.props.PropsKeyModifyRequest;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
/**
 * Title: PropsKey表
 * Description: 属性Key修改方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-4
 */
@Service
public class PropsKeyUpdateService extends SingleTemplate<PropsKeyModifyRequest, Boolean> {

    @Autowired
    private PropsKeyMapper propsKeyMapper;

    @Override
    protected Boolean callInner(CommonRequest<PropsKeyModifyRequest> request) throws Exception {
        Boolean flag = false;
        //从请求中获取对象
        PropsKeyModifyRequest propsKeyModifyRequest = request.getRequest();
//        //验证参数是否合法,不合法则返回错误code
//        CommonResponse<Boolean> response = new CommonResponse<>();
//        if ( ValueUtil.isEmpty( propsKeyModifyRequest )
//                || ValueUtil.isEmpty(propsKeyModifyRequest.getId() )
//                || ValueUtil.isEmpty(propsKeyModifyRequest.getKey() )
//                ) {
//            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
//            response.setContent(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseDesc());
//            response.setResponse(false);
//            return response;
//        }

        //组合实体对象
        PropsKey propsKey = new PropsKey();
        //复制对象(也可以不用BeanUtils,逐个对象复制,也可以自己预先写好一个转换的类)
        BeanUtils.copyProperties(propsKey, propsKeyModifyRequest);
        //设置修改时间(不应该在这里设置,应该在OSSservice的控制层加入这个时间,PropsKeyModifyRequest应该加多一个updateTime的属性)
        propsKey.setUpdateTime(new Date());
        //修改对象
        int result = propsKeyMapper.updateByPrimaryKeySelective(propsKey);

        //组合返回对象
        if (result > 0) {
//            response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
//            response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
            flag = true;
        }
//        response.setResponse(flag);
//        return response;
        return flag;
    }
}
