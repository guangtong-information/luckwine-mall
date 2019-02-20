package com.luckwine.goods.model.service;

import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.request.props.*;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;

import java.util.List;

/**
 * 商品属性服务类.
 */
public interface PropsService {


    /**   新增属性key   */
    CommonResponse<Boolean> saveKey(CommonRequest<PropsKeySaveRequest> request);

    /**  删除属性key   */
    CommonResponse<Boolean> deleteKey(CommonRequest<PropsKeyDeleteRequest> request);

    /**   修改属性key   */
    CommonResponse<Boolean> modifyKey(CommonRequest<PropsKeyModifyRequest> request);

    /**   分页查询属性key  */
    CommonQueryPageResponse<List<PropsKey>> pageKey(CommonQueryPageRequest<PropsKeyPageRequest> request);


    /**  新增属性value    */
    CommonResponse<Boolean> saveValue(CommonRequest<PropsValueSaveRequest> request);

    /**   删除属性value   */
    CommonResponse<Boolean> deleteValue(CommonRequest<PropsValueDeleteRequest> request);

    /**  修改属性value    */
    CommonResponse<Boolean> modifyValue(CommonRequest<PropsValueModifyRequest> request);

    /**   分页查询属性value   */
    CommonQueryPageResponse<List<PropsValue>> pageValue(CommonQueryPageRequest<PropsValuePageRequest> request);


}
