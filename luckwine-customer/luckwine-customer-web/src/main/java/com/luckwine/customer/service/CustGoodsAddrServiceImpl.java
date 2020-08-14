package com.luckwine.customer.service;

import com.luckwine.customer.handle.local.CustGoodsAddrPageService;
import com.luckwine.customer.handle.local.DeleteCustGoodsAddrService;
import com.luckwine.customer.handle.local.InsertCustGoodsAddrService;
import com.luckwine.customer.handle.local.UpdateCustGoodsAddrService;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.extern.java.Log;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName CustGoodsAddrServiceImpl
 * @Description 客户收货地址 服务实现类
 **/
@DubboService(validation = "true")
@Log
public class CustGoodsAddrServiceImpl implements CustGoodsAddrService {

    @Autowired
    private InsertCustGoodsAddrService insertCustGoodsAddrService;
    @Autowired
    private UpdateCustGoodsAddrService updateCustGoodsAddrService;
    @Autowired
    private DeleteCustGoodsAddrService deleteCustGoodsAddrService;
    @Autowired
    private CustGoodsAddrPageService custGoodsAddrPageService;


    /**
     * 客户收货地址分页查询
     * @param request
     * @return
     * @Author yewenqing 叶文清
     * @Date 2018/10/3 11:44
     * Dubbo debug:     invoke CustGoodsAddrService.queryCustGoodsAddrPage({"class":"com.luckwine.parent.entitybase.request.CommonQueryPageRequest","pageNo":"1","pageSize":"10","request":{"class":"com.luckwine.customer.model.base.CustGoodsAddr"}})
     */
    @Override
    public CommonQueryPageResponse<List<CustGoodsAddr>> queryCustGoodsAddrPage(CommonQueryPageRequest<CustGoodsAddr> request){
        if( null == request.getPageNo() || null == request.getPageSize() ){
//            System.out.println("request.PageNo or PageSize is null--");
//            log.log(java.util.logging.Level., "request.PageNo or PageSize is null" );
            throw new ParamErrorException("request.PageNo or PageSize is null");
        }
        return custGoodsAddrPageService.call(request);
    }

    @Override
    public CommonResponse<Boolean> insertCustGoodsAddr(CommonRequest<CustGoodsAddr> request) {
        return insertCustGoodsAddrService.call(request);
    }

    @Override
    public CommonResponse<Boolean> updateCustGoodsAddr(CommonRequest<CustGoodsAddr> request) {
        return updateCustGoodsAddrService.call(request);
    }

    @Override
    public CommonResponse<Boolean> delCustGoodsAddr(CommonRequest<CustGoodsAddr> request) {
        return deleteCustGoodsAddrService.call(request);

    }
}
