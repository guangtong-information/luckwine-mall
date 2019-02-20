package com.luckwine.customer.handle.local;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luckwine.customer.dao.CustGoodsAddrMapper;
import com.luckwine.customer.model.base.CustGoodsAddr;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.template.QueryPageTemplate;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CustGoodsAddrPageService
 * @Description 客户收货地址分页查询
 * @Author yewenqing 叶文清
 * @Date 2018/9/26 20:42
 * @Version 1.0
 **/
@Service
@Log
@Transactional
public class CustGoodsAddrPageService extends QueryPageTemplate<CustGoodsAddr, CustGoodsAddr> {

    @Resource
    private CustGoodsAddrMapper custGoodsAddrMapper;

    @Override
    protected List<CustGoodsAddr> callInner( CommonQueryPageRequest<CustGoodsAddr> request ) throws Exception {

        CustGoodsAddr custGoodsAddr = request.getRequest();
        QueryWrapper<CustGoodsAddr> entityWrapper = new QueryWrapper<>();
        //设置实体对象(实体里面的字段,如果有值,是可以where查询的)
        entityWrapper.setEntity(custGoodsAddr);
        //调用mybatisplus的方法进行查询,查询前会自动调用PlagHelper获取参数,QueryPageTemplate会自动从request中获取并设置参数(PageHelper.startPage(request.getPageNo(), request.getPageSize());这句)
        //PlagHelper重写了Mybatis的监听方法；在Mybatis执行sql的前面获取分页的参数，并且组装了分页sql
        return custGoodsAddrMapper.selectList(entityWrapper);

    }






}

