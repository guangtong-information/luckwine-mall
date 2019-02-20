package com.luckwine.customer.handle.remote;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.template.SingleRemoteTemplate;
//import com.luckwine.synthesize.model.SmsTemplate;
/**
 * @ClassName SendSmsRemoteService
 * @
 * @Description 下发短信 (dubbo调用综合域短信接口,还需要等待综合组上传短信实体对象类与实际调用方法)
 * @Author yewenqing 叶文清
 * @Date 2018/9/26 21:07
 * @Version 1.0
 **/
public class SendSmsRemoteService extends SingleRemoteTemplate<Object, Object> {

    @Override
    protected CommonResponse<Object> callRemote(CommonRequest<Object> request){
        //TODO 等待综合组完成综合域短信接口 [叶文清 20180926]
        // 综合域的jar包还没有上传私库,所以不能使用 SmsTemplate的实体类
        return null;
    }
}
