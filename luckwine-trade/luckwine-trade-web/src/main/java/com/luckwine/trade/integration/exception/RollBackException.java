//package com.luckwine.createOrder.integration.exception;
//
//import com.luckwine.goods.model.request.sku.SkuStockVO;
//import com.luckwine.parent.entitybase.exception.CommonException;
//import com.luckwine.createOrder.integration.constant.ExceptionDeal;
//
//import java.util.List;
//
//public class RollBackException<T> extends CommonException {
//
//    private ExceptionDeal deal;
//    private List<SkuStockVO> skuStockVO;
//
//    /**
//     * 回滚异常
//     *
//     * @param code    异常响应吗
//     * @param message 异常信息
//     * @param deal    异常操作
//     */
//    public RollBackException(String code, String message,List<SkuStockVO> skuStockVO) {
//        super(code, message);
//        this.deal = deal;
//        this.dealData = dealData;
//    }
//}
