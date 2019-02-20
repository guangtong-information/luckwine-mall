package com.luckwine.trade.integration.service.business.core;

import com.luckwine.goods.model.base.vo.SkuDetail;
import com.luckwine.goods.model.enums.SkuStatus;
import com.luckwine.goods.model.enums.SpuStatus;
import com.luckwine.goods.model.enums.StockOperate;
import com.luckwine.goods.model.request.sku.SkuDetailGetByIdsRequest;
import com.luckwine.goods.model.request.sku.SkuStockModifyRequest;
import com.luckwine.goods.model.request.sku.SkuStockVO;
import com.luckwine.marketing.model.request.coupon.MarketRequestSeq;
import com.luckwine.marketing.model.request.coupon.UserMarketingCouponReq;
import com.luckwine.marketing.model.request.enums.MarketingExpensesEnum;
import com.luckwine.marketing.model.response.UserMarketingCouponResq;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.enums.*;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.trade.entity.TradeCapitalStatement;
import com.luckwine.trade.entity.TradeGoodsOrder;
import com.luckwine.trade.entity.TradeMainOrder;
import com.luckwine.trade.entity.TradeSubOrder;
import com.luckwine.trade.handle.remote.goods.GetSkuDetailRemoteService;
import com.luckwine.trade.handle.remote.goods.StockModifyRemoteService;
import com.luckwine.trade.handle.remote.marketing.UserCouponsRemoteService;
import com.luckwine.trade.integration.carrier.TradeCarrier;
import com.luckwine.trade.integration.carrier.vo.OrderCarrier;
import com.luckwine.trade.model.request.ConsumeOrderRequest;
import com.luckwine.trade.model.request.CouponRequestSeq;
import com.luckwine.trade.model.request.GoodsOrderRequest;
import com.luckwine.trade.model.request.RechargeOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 消费交易下单计算服务
 * Created by Winlone on 2018/9/20.
 */
@Service
@Slf4j
public class PreOrderFlow {

    @Autowired
    private GetSkuDetailRemoteService getSkuDetailRemoteService;

    @Autowired
    private StockModifyRemoteService stockModifyRemoteService;

    @Autowired
    private UserCouponsRemoteService userCouponsRemoteService;

    @Value("${store}")
    private String store;

    @Value("${env}")
    private String env;

    /**
     * 充值-下单前的金额计算，实体数据组合
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier orderRechargeCalculation(TradeCarrier tradeCarrier) {
        //下单接口总请求
        CommonRequest<RechargeOrderRequest> request = tradeCarrier.getRequest();
        //下单口参数请求
        RechargeOrderRequest createOrderRequest = request.getRequest();

        //主单号
        String mainOrder = SequenceUtil.genSequence(SequenceCode.MAINORDER, env.toString());

        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        //==========================================
        //============ a.主单：买家订单 ============
        //==========================================
        //主单载体
        TradeMainOrder tradeMainOrder = new TradeMainOrder();
        tradeMainOrder.setMainOrderCode(mainOrder);
        //充值单
        tradeMainOrder.setTransType(TransType.RECHARGE.getCode());
        //等待支付
        tradeMainOrder.setOrderStatus(OrderStatus.PAYMENT_WAITING.getCode());
        tradeMainOrder.setChannelCode(request.getChannelCode());
        tradeMainOrder.setBuyLoginAccount(createOrderRequest.getBuyLoginAccount());
        tradeMainOrder.setTotalAmount(createOrderRequest.getAmount());
        tradeMainOrder.setPayAmount(createOrderRequest.getAmount());
        tradeMainOrder.setDiscAmount(BigDecimal.ZERO);
        tradeMainOrder.setNote(createOrderRequest.getNote());
        tradeMainOrder.setCreateTime(new Date());
        orderCarrier.setTradeMainOrder(tradeMainOrder);

        //赋值订单载体信息
        tradeCarrier.setOrderCarrier(orderCarrier);

        return tradeCarrier;
    }

    /**
     * 消费-下单前的金额计算，实体数据组合
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier orderConsumeCalculation(TradeCarrier tradeCarrier) {
        //下单接口总请求
        CommonRequest<ConsumeOrderRequest> request = tradeCarrier.getRequest();
        //下单口参数请求
        ConsumeOrderRequest createOrderRequest = request.getRequest();
        //请求购买商品列表orderGoodsList
        List<GoodsOrderRequest> orderGoodsList = createOrderRequest.getOrderGoodsList();

        //====================================
        //============ 1.sku排重 =============
        //====================================
        //排重hashmap
        Map<String, GoodsOrderRequest> skuIdsMap = new HashMap<>();
        //批量查询商品sku集合
        List<Long> skuIds = new ArrayList<>();
        //批量减库存集合
        List<SkuStockVO> stocks = new ArrayList<>();
        for (int i = 0; i < orderGoodsList.size(); i++) {
            String skuId = orderGoodsList.get(i).getSkuId();
            Long count = orderGoodsList.get(i).getGoodsCount();
            //sku排重复和大于0
            if (StringUtils.isNotBlank(skuId) && !skuIdsMap.containsKey(skuId)) {
                //如果没有重复，则加入排重hashmap
                skuIdsMap.put(skuId, orderGoodsList.get(i));
                //批量查询商品sku集合
                Long s = Long.parseLong(skuId);
                skuIds.add(s);
                //批量减库存集合
                SkuStockVO skuStockVO = new SkuStockVO();
                skuStockVO.setId(s);
                skuStockVO.setQuantity(count);
                skuStockVO.setStockOperate(StockOperate.DECR);
                stocks.add(skuStockVO);
            } else {
                //有重复就要删除orderGoodsList
                orderGoodsList.remove(i);
            }
        }
        //排重后的商品列表，放回请求列表
        createOrderRequest.setOrderGoodsList(orderGoodsList);
        request.setRequest(createOrderRequest);
        //存入订单载体：批量查询商品sku集合
        tradeCarrier.setSkuIds(skuIds);
        //存入订单载体：批量减库存集合
        tradeCarrier.setStocks(stocks);

        //=============================================
        //============ 2.批量获取商品信息 =============
        //=============================================
        CommonRequest<SkuDetailGetByIdsRequest> skuDetailGetByIdsRequestRemote = new CommonRequest<>();
        //复制CommonRequest基础对象
        BeanUtils.copyProperties(request, skuDetailGetByIdsRequestRemote);
        SkuDetailGetByIdsRequest skuDetailGetByIdsRequest = new SkuDetailGetByIdsRequest();
        skuDetailGetByIdsRequest.setSkuIds(skuIds);
        skuDetailGetByIdsRequestRemote.setRequest(skuDetailGetByIdsRequest);
        CommonResponse<List<SkuDetail>> getSkuDetailRemoteRes = getSkuDetailRemoteService.call(skuDetailGetByIdsRequestRemote);
        //判断查询结果
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(getSkuDetailRemoteRes.getCode())) {
            throw new CommonException(getSkuDetailRemoteRes.getCode(), getSkuDetailRemoteRes.getContent());
        }
        List<SkuDetail> skuDetailList = getSkuDetailRemoteRes.getResponse();
        for (SkuDetail skuDetail : skuDetailList) {
            //下架状态不能购买
            if (skuDetail.getStatus().equals(SpuStatus.IN_STOCK.name()) || skuDetail.getSkuStatus().equals(SkuStatus.IN_STOCK.name())) {
                throw new CommonException(getSkuDetailRemoteRes.getCode(), "商品" + skuDetail.getSkuName() + "下架不能购买");
            }
            //库存不足不能购买
            GoodsOrderRequest goodsOrderRequest = skuIdsMap.get(skuDetail.getSkuId().toString());
            if (skuDetail.getQuantity() < goodsOrderRequest.getGoodsCount()) {
                throw new CommonException(getSkuDetailRemoteRes.getCode(), "商品" + skuDetail.getSkuName() + "库存不足");
            }
        }

        //按卖家id分组，一个卖家一个子单(说明：当前商品模块没有卖家字段，当作卖家只有酒缘网官方旗舰店一个)
        //Map<String, List<SkuDetail>> groupGoods = acctList.stream().collect(Collectors.groupingBy(SkuDetail::getBrandName));

        //核心订单载体
        OrderCarrier orderCarrier = tradeCarrier.getOrderCarrier();
        //==============================================================
        //============ 3.赋值mainOrderCarrier的订单消息载体 ============
        //==============================================================
        //主单载体
        TradeMainOrder tradeMainOrder = new TradeMainOrder();
        //子单载体
        List<TradeSubOrder> tradeSubOrderList = new ArrayList<>();
        TradeSubOrder tradeSubOrder = new TradeSubOrder();
        //商品单载体
        List<TradeGoodsOrder> tradeGoodsOrderList = new ArrayList<>();

        //主单号
        String mainOrder = SequenceUtil.genSequence(SequenceCode.MAINORDER, env);
        //子单号
        String subOrder = SequenceUtil.genSequence(SequenceCode.SUBORDER, env);
        //总金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        //购买商品总数
        Long buyCount = 0L;
        //=======================================
        //============== a.商品单 ===============
        //=======================================
        for (int i = 0; i < skuDetailList.size(); i++) {
            //远程获取的商品数据信息
            SkuDetail skuDetail = skuDetailList.get(i);
            //请求下单的商品参数
            GoodsOrderRequest req = skuIdsMap.get(skuDetail.getSkuId().toString());

            //商品单载体参数赋值
            TradeGoodsOrder tradeGoodsOrder = new TradeGoodsOrder();
            //单号信息
            tradeGoodsOrder.setGoodsOrderCode(SequenceUtil.genSequence(SequenceCode.GOODSORDER, env));
            tradeGoodsOrder.setMainOrderCode(mainOrder);
            tradeGoodsOrder.setSubOrderCode(subOrder);
            //spu信息
            tradeGoodsOrder.setSpuId(skuDetail.getSpuId());
            tradeGoodsOrder.setGoodsName(skuDetail.getGoodsName());
            tradeGoodsOrder.setSpuPropsStr(skuDetail.getPropsStr());
            //sku信息
            tradeGoodsOrder.setSkuId(skuDetail.getSkuId().toString());
            tradeGoodsOrder.setSkuName(skuDetail.getSkuName());
            tradeGoodsOrder.setSkuPropsStr(skuDetail.getSkuPropsStr());
            //其他商品信息
            tradeGoodsOrder.setBizStatus(BizStatus.BIZ_WAITING.getCode());
            tradeGoodsOrder.setCategoryName(skuDetail.getCategoryName());
            tradeGoodsOrder.setBrandName(skuDetail.getBrandName());
            tradeGoodsOrder.setPicPath(skuDetail.getPicPath());
            tradeGoodsOrder.setGoodsPrice(skuDetail.getPrice());
            tradeGoodsOrder.setGoodsCount(req.getGoodsCount());
            //商品总价 = 商品价格 * 数量
            BigDecimal goodsPrice = new BigDecimal(skuDetail.getPrice().toString());
            BigDecimal goodsNum = new BigDecimal(req.getGoodsCount().toString());
            BigDecimal goodsTotal = goodsPrice.multiply(goodsNum);

            tradeGoodsOrder.setTotalAmount(goodsTotal);
            tradeGoodsOrder.setPayAmount(goodsTotal);
            tradeGoodsOrder.setDiscAmount(BigDecimal.ZERO);

            tradeGoodsOrder.setCreateTime(new Date());
            // 关键位置----------商品信息加入商品单列表
            tradeGoodsOrderList.add(tradeGoodsOrder);

            //主单总金额累加
            totalAmount = totalAmount.add(goodsTotal);

            //购买商品总数
            buyCount = buyCount + req.getGoodsCount();
        }
        orderCarrier.setTradeGoodsOrder(tradeGoodsOrderList);
        //==========================================
        //============ b.子单：卖家订单 ============
        //==========================================
        tradeSubOrder.setSubOrderCode(subOrder);
        tradeSubOrder.setMainOrderCode(mainOrder);
        tradeSubOrder.setOrderStatus(OrderStatus.PAYMENT_WAITING.getCode());
        tradeSubOrder.setChannelCode(request.getChannelCode());
        tradeSubOrder.setBuyLoginAccount(createOrderRequest.getBuyLoginAccount());
        //卖家默认是酒缘网官方旗舰店（第三方登录账号）
        tradeSubOrder.setSaleLoginAccount(store);
        tradeSubOrder.setTotalAmount(totalAmount);
        tradeSubOrder.setPayAmount(totalAmount);
        tradeSubOrder.setDiscAmount(BigDecimal.ZERO);
        tradeSubOrder.setGoodsCount(buyCount);
        tradeSubOrder.setNote(createOrderRequest.getNote());
        tradeSubOrder.setCreateTime(new Date());
        // 关键位置----------一个子单加入子单列表
        tradeSubOrderList.add(tradeSubOrder);
        orderCarrier.setTradeSubOrder(tradeSubOrderList);
        //==========================================
        //============ c.主单：买家订单 ============
        //==========================================
        tradeMainOrder.setMainOrderCode(mainOrder);
        //消费单
        tradeMainOrder.setTransType(TransType.CONSUME.getCode());
        //等待支付
        tradeMainOrder.setOrderStatus(OrderStatus.PAYMENT_WAITING.getCode());
        tradeMainOrder.setChannelCode(request.getChannelCode());
        tradeMainOrder.setBuyLoginAccount(createOrderRequest.getBuyLoginAccount());
        tradeMainOrder.setTotalAmount(totalAmount);
        tradeMainOrder.setPayAmount(totalAmount);
        tradeMainOrder.setDiscAmount(BigDecimal.ZERO);
        tradeMainOrder.setNote(createOrderRequest.getNote());
        tradeMainOrder.setCreateTime(new Date());
        // 关键位置----------交易工作流载体 加入 主单、子单、商品单信息
        orderCarrier.setTradeMainOrder(tradeMainOrder);

        //==========================================
        //============ d.物流单：买家收货地址 ============
        //==========================================
        //TODO 物流单实体对象赋值

        //赋值订单载体信息
        tradeCarrier.setOrderCarrier(orderCarrier);
        return tradeCarrier;
    }

    /**
     * 商品库存减少
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier reduceGoodsStocks(TradeCarrier tradeCarrier) {
        //下单接口总请求
        CommonRequest<ConsumeOrderRequest> request = tradeCarrier.getRequest();

        //组装报文
        CommonRequest<SkuStockModifyRequest> remoteRequest = new CommonRequest<>();
        //复制CommonRequest基础对象
        BeanUtils.copyProperties(request, remoteRequest);
        //远程商品库存实体
        SkuStockModifyRequest skuStockModifyRequest = new SkuStockModifyRequest();
        skuStockModifyRequest.setStocks(tradeCarrier.getStocks());
        remoteRequest.setRequest(skuStockModifyRequest);

        //调用商品模块，减少库存
        CommonResponse<Boolean> response = stockModifyRemoteService.call(remoteRequest);
        //抛出异常
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(response.getCode())) {
            throw new CommonException(response.getCode(), response.getContent());
        }

        return tradeCarrier;
    }

    /**
     * 商品库存增加-用于回滚库存
     *
     * @param tradeCarrier
     * @return
     */
    public TradeCarrier rollbackGoodsStocks(TradeCarrier tradeCarrier) {
        //下单接口总请求
        CommonRequest<ConsumeOrderRequest> request = tradeCarrier.getRequest();

        //组装报文
        CommonRequest<SkuStockModifyRequest> remoteRequest = new CommonRequest<>();
        //复制CommonRequest基础对象
        BeanUtils.copyProperties(request, remoteRequest);
        //远程商品库存实体
        SkuStockModifyRequest skuStockModifyRequest = new SkuStockModifyRequest();
        //回滚报文组装
        List<SkuStockVO> stocks = tradeCarrier.getStocks();
        List<SkuStockVO> rollbackStocks = new ArrayList<>();
        for (int i = 0; i < tradeCarrier.getStocks().size(); i++) {
            SkuStockVO skuStockVO = new SkuStockVO();
            skuStockVO.setId(stocks.get(i).getId());
            skuStockVO.setQuantity(stocks.get(i).getQuantity());
            skuStockVO.setStockOperate(StockOperate.INCR);
            rollbackStocks.add(skuStockVO);
        }
        //回滚库存（增加）
        skuStockModifyRequest.setStocks(rollbackStocks);
        remoteRequest.setRequest(skuStockModifyRequest);

        //调用商品模块，减少库存
        CommonResponse<Boolean> response = stockModifyRemoteService.call(remoteRequest);
        //抛出异常
        if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(response.getCode())) {
            throw new CommonException(response.getCode(), response.getContent());
        }

        return tradeCarrier;
    }

    /**
     * 使用优惠券
     *
     * @param tradeCarrier
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TradeCarrier userCoupons(TradeCarrier tradeCarrier) {
        CommonRequest<ConsumeOrderRequest> request = tradeCarrier.getRequest();  //当前：请求对象
        TradeMainOrder tradeMainOrder = tradeCarrier.getOrderCarrier().getTradeMainOrder();  //主单
        List<TradeSubOrder> tradeSubOrder = tradeCarrier.getOrderCarrier().getTradeSubOrder();  //子单
        List<TradeGoodsOrder> tradeGoodsOrder = tradeCarrier.getOrderCarrier().getTradeGoodsOrder();  //商品单
        ConsumeOrderRequest consumeOrderRequest = request.getRequest();   //当前：消费请求
        List<CouponRequestSeq> marketRequestSeqListReq = consumeOrderRequest.getMarketRequestSeqList();  //当前：使用的优惠券列表

        //优惠券大于0，才会使用优惠券
        if (marketRequestSeqListReq != null && marketRequestSeqListReq.size() > 0) {
            //============= 一、公用请求参数赋值 =============
            CommonRequest<UserMarketingCouponReq> remoteRequest = new CommonRequest<>();
            BeanUtils.copyProperties(request, remoteRequest);
            //远程：1.设置优惠券实体
            UserMarketingCouponReq userMarketingCouponReq = new UserMarketingCouponReq();
            userMarketingCouponReq.setLoginAccount(consumeOrderRequest.getBuyLoginAccount());  //登录号
            userMarketingCouponReq.setOrderNo(tradeMainOrder.getMainOrderCode());   //主单号

            //============= 二、循环赋值优惠券列表 =============
            for (CouponRequestSeq couponRequestSeq : marketRequestSeqListReq) {
                //生成优惠券资金流水号
                String cap_seq = SequenceUtil.genSequence(SequenceCode.CAPITAL, env);

                //远程：2.设置使用的优惠券列表
                List<MarketRequestSeq> marketRequestSeqListRemote = new ArrayList<>();
                //远程：3.设置一张优惠券对应多个商品
                MarketRequestSeq marketRequestSeq = new MarketRequestSeq();
                marketRequestSeq.setCouponNum(couponRequestSeq.getCouponNum());  //优惠券号
                marketRequestSeq.setRequestSeq(cap_seq);   //优惠券资金流水号
                marketRequestSeq.setSkuIdSubList(couponRequestSeq.getSkuIdSubList());  //优惠券对应的商品列表
                marketRequestSeqListRemote.add(marketRequestSeq);
                userMarketingCouponReq.setMarketRequestSeqList(marketRequestSeqListRemote);
            }

            //============= 三、远程调用 =============
            //远程：3.放入最终remoteRequest远程实体
            remoteRequest.setRequest(userMarketingCouponReq);
            //调用marketing, 消费优惠券
            CommonResponse<List<UserMarketingCouponResq>> remoteRes = userCouponsRemoteService.call(remoteRequest);
            if (!ResponseCodeConstant.SUCCESS.getResponseCode().equals(remoteRes.getCode())) {
                throw new CommonException(ResponseCodeConstant.SYS_EXCEPTION.getResponseCode(), "登录号[" +
                        consumeOrderRequest.getBuyLoginAccount() + "]消费优惠券号[" + marketRequestSeqListReq.toString() + "]异常");
            }

            //============= 四、插入优惠券资金流水 =============
            if (remoteRes.getResponse() != null) {
                BigDecimal discAmount = new BigDecimal(0);
                //返回使用了每个优惠券流水对应的优惠商品
                for (UserMarketingCouponResq userMarketingCouponResq : remoteRes.getResponse()) {
                    TradeCapitalStatement tradeCapitalStatement = new TradeCapitalStatement();
                    //======= 单号 ==========
                    tradeCapitalStatement.setCapSeq(userMarketingCouponResq.getRequestSeq());
                    tradeCapitalStatement.setMainOrderCode(tradeMainOrder.getMainOrderCode());
                    //======== 资金相关信息 ==========
                    tradeCapitalStatement.setCreateTime(new Date());
                    tradeCapitalStatement.setAmount(userMarketingCouponResq.getDiscountAmount());
                    tradeCapitalStatement.setCapModule(CapModule.MARKETING.getCode());
                    tradeCapitalStatement.setCapSysType(CapSysType.COUPON.getCode());
                    //======== 返回流水信息 ==========
                    tradeCapitalStatement.setCapBackCode(remoteRes.getCode());
                    tradeCapitalStatement.setCapBackDesc(remoteRes.getContent());
                    tradeCapitalStatement.setCapBackSeq(userMarketingCouponResq.getMarketOrderno());
                    //======== 支付信息 ==========
                    tradeCapitalStatement.setPayOper(PayOper.PAY.getCode());
                    tradeCapitalStatement.setPayTime(userMarketingCouponResq.getCreateTime());
                    //======== 成功使用优惠券后，记录和摊分优惠金额 ==========
                    if (MarketingExpensesEnum.STATUS_SUCCESS.getCode().equals(userMarketingCouponResq.getStatus())) {
                        tradeCapitalStatement.setPayStatus(PayStatus.PAYMENT_SUCCEED.getCode());

                        //循环累加总优惠金额
                        discAmount = discAmount.add(userMarketingCouponResq.getDiscountAmount());

                        //======== 优惠券摊分每个商品优惠金额 ========
                        List<Long> skuIdSubList = userMarketingCouponResq.getSkuIdSubList();
                        BigDecimal goodsCount = new BigDecimal(skuIdSubList.size());


                        //计算每个商品的价格
                        BigDecimal goodsDiscountAmount = userMarketingCouponResq.getDiscountAmount().divide(goodsCount, 2, RoundingMode.DOWN);
                        //余数
                        BigDecimal r = userMarketingCouponResq.getDiscountAmount().remainder(goodsCount);
                        BigDecimal remainder = new BigDecimal(0);
                        if (r.compareTo(BigDecimal.ZERO) == 1) {
                            remainder = userMarketingCouponResq.getDiscountAmount().subtract(goodsDiscountAmount.multiply(goodsCount));
                        }
                        int num = 0;
                        List<TradeGoodsOrder> tradeGoodsOrderTemp = new ArrayList<>();
                        for (TradeGoodsOrder m : tradeGoodsOrder) {
                            if (num == 0) {
                                //第一个商品优惠价，加上余数
                                m.setDiscAmount(goodsDiscountAmount.add(remainder));
                            } else {
                                //其余的只使用优惠价
                                m.setDiscAmount(goodsDiscountAmount);
                            }
                            //更新商品list的优惠价格
                            tradeGoodsOrderTemp.add(m);
                        }
                        tradeCarrier.getOrderCarrier().setTradeGoodsOrder(tradeGoodsOrderTemp);
                        //======== 优惠券摊分每个商品优惠金额-end ========
                    } else {
                        tradeCapitalStatement.setPayStatus(PayStatus.PAYMENT_FAIL.getCode());
                    }

                    //======== 获取优惠券号 =========
                    tradeCapitalStatement.setCapAcctCode(userMarketingCouponResq.getCouponNum());

                    //插入优惠券资金流水
                    tradeCarrier.getOrderCarrier().setDiscountTradeCapitalStatement(tradeCapitalStatement);

                }

                //主单优惠金额汇总
                tradeMainOrder.setDiscAmount(discAmount);
                tradeCarrier.getOrderCarrier().setTradeMainOrder(tradeMainOrder);

                //子单优惠金额汇总
                if (tradeSubOrder.size() > 0) {
                    tradeSubOrder.get(0).setDiscAmount(discAmount);
                    tradeCarrier.getOrderCarrier().setTradeSubOrder(tradeSubOrder);
                }
            }
        }

        return tradeCarrier;
    }


}
