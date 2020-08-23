package com.luckwine.goods.service;

import com.luckwine.goods.handle.local.props.*;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.request.props.*;
import com.luckwine.goods.model.service.PropsService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * Title: 商品属性 实现类
 * Description: 商品属性 实现类
 * Company: Copyright @ 2018 北大青鸟广力学院版权所有
 *
 * @version 1.0
 * @author: 叶文清
 * @date: 2018-11-4
 */
@DubboService
public class PropsServiceImpl implements PropsService {

    @Autowired
    private PropsKeySaveService propsKeySaveService;

    @Autowired
    private PropsKeyUpdateService propsKeyUpdateService;

    @Autowired
    private PropsKeyDeleteService propsKeyDeleteService;

    @Autowired
    private PropsKeyPageService propsKeyPageService;

    @Autowired
    private PropsValueSaveService propsValueSaveService;

    @Autowired
    private PropsValueUpdateService propsValueUpdateService;

    @Autowired
    private PropsValueDeleteService propsValueDeleteService;

    @Autowired
    private PropsValuePageService propsValuePageService;

    /**
     * 功能描述: 商品属性Key 新增
     * @param:  CommonRequest<PropsKeySaveRequest>
     * @return: CommonResponse<Boolean>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04
     * Dubbo debug: invoke PropsService.saveKey({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.props.PropsKeySaveRequest","key":"KeyTest"}})
     */
    @Override
    public CommonResponse<Boolean> saveKey(CommonRequest<PropsKeySaveRequest> request) {
        return propsKeySaveService.call(request);
    }

    /**
     * 功能描述: 商品属性Key 删除
     * @param:  CommonRequest<PropsKeyDeleteRequest>
     * @return: CommonResponse<Boolean>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04
     * Dubbo debug: invoke PropsService.deleteKey({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.props.PropsKeyDeleteRequest","id":"36"}})
     */
    @Override
    public CommonResponse<Boolean> deleteKey(CommonRequest<PropsKeyDeleteRequest> request) {
        return propsKeyDeleteService.call(request);
    }

    /**
     * 功能描述: 商品属性Key 修改
     * @param:  CommonRequest<PropsKeyModifyRequest>
     * @return: CommonResponse<Boolean>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04
     * Dubbo debug: invoke PropsService.modifyKey({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.props.PropsKeyModifyRequest","id":"37","key":"KeyTest2"}})
     */
    @Override
    public CommonResponse<Boolean> modifyKey(CommonRequest<PropsKeyModifyRequest> request) {
        return propsKeyUpdateService.call(request);
    }

    /**
     * 功能描述: 分页查询 商品属性Key
     * @param:  CommonQueryPageRequest<PropsKeyPageRequest>
     * @return: CommonQueryPageResponse<List<PropsKey>>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04 17:35
     * Dubbo debug: invoke PropsService.pageKey({"class":"com.luckwine.parent.entitybase.request.CommonQueryPageRequest","pageNo":"1","pageSize":"10","request":{"class":"com.luckwine.goods.model.request.props.PropsKeyPageRequest","key":"产区"}})
     */
    @Override
    public CommonQueryPageResponse<List<PropsKey>> pageKey(CommonQueryPageRequest<PropsKeyPageRequest> request) {
        return propsKeyPageService.call(request);
    }

    /**
     * 功能描述: 商品属性Value 新增
     * @param:  CommonRequest<PropsValueSaveRequest>
     * @return: CommonResponse<Boolean>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04
     * Dubbo debug: invoke PropsService.saveValue({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.props.PropsValueSaveRequest","keyId":"XXX","value":"XXX"}})
     */
    @Override
    public CommonResponse<Boolean> saveValue(CommonRequest<PropsValueSaveRequest> request) {
        return propsValueSaveService.call(request);
    }

    /**
     * 功能描述: 商品属性Value 删除
     * @param:  CommonRequest<PropsValueDeleteRequest>
     * @return: CommonResponse<Boolean>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04
     * Dubbo debug: invoke PropsService.deleteValue({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.props.PropsValueDeleteRequest","Id":"XXX"}})
     */
    @Override
    public CommonResponse<Boolean> deleteValue(CommonRequest<PropsValueDeleteRequest> request) {
        return propsValueDeleteService.call(request);
    }

    /**
     * 功能描述: 商品属性Value 修改
     * @param:  CommonRequest<PropsValueModifyRequest>
     * @return: CommonResponse<Boolean>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04
     * Dubbo debug: invoke PropsService.modifyValue({"class":"com.luckwine.parent.entitybase.request.CommonRequest","request":{"class":"com.luckwine.goods.model.request.props.PropsValueModifyRequest","id":"XXX","keyId":"XXX","value":"XXX"}})
     */
    @Override
    public CommonResponse<Boolean> modifyValue(CommonRequest<PropsValueModifyRequest> request) {
        return propsValueUpdateService.call(request);
    }

    /**
     * 功能描述: 分页查询 商品属性Value
     * @param:  CommonQueryPageRequest<PropsValuePageRequest>
     * @return: CommonQueryPageResponse<List<PropsValue>>
     * @Author yewenqing 叶文清
     * @Date 2018-11-04 17:35
     * Dubbo debug: invoke PropsService.pageValue({"class":"com.luckwine.parent.entitybase.request.CommonQueryPageRequest","pageNo":"1","pageSize":"10","request":{"class":"com.luckwine.goods.model.request.props.PropsValuePageRequest","keyId":"37"}})
     */
    @Override
    public CommonQueryPageResponse<List<PropsValue>> pageValue(CommonQueryPageRequest<PropsValuePageRequest> request) {
        return propsValuePageService.call(request);
    }
}
