package com.luckwine.goods.handle.local.props;

import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.model.request.props.PropsKeyDeleteRequest;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.parent.tools.common.ValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Title: PropsKey表
 * Description: 属性Key删除方法
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-4
 */
@Service
public class PropsKeyDeleteService extends SingleTemplate<PropsKeyDeleteRequest, Boolean> {

    @Autowired
    private PropsKeyMapper propsKeyMapper;

    @Override
    protected Boolean callInner(CommonRequest<PropsKeyDeleteRequest> request) throws Exception {
        Boolean flag = false;
        //从请求中获取对象
        PropsKeyDeleteRequest propsKeyDeleteRequest = request.getRequest();

//        //验证参数是否合法,不合法则返回错误code
//        CommonResponse<Boolean> response = new CommonResponse<>();
//        if ( ValueUtil.isEmpty( propsKeyDeleteRequest )
//                || ValueUtil.isEmpty(propsKeyDeleteRequest.getId() )
//                ) {
//            response.setCode(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode());
//            response.setContent(ResponseCodeConstant.REQUEST_ILLEGAL.getResponseDesc());
//            response.setResponse(flag);
//            return response;
//        }

        //获取ID
        Long id = propsKeyDeleteRequest.getId();

        //删除对象
        int result = propsKeyMapper.deleteByPrimaryKey(id);

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
