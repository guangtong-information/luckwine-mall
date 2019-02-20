package com.luckwine.trade.integration.service.base;


import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.trade.integration.validator.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * 工作流异常处理
 * Created by Winlone on 2018/9/20.
 */
@Component
@Slf4j
public class DefaultExceptionService {


    public CommonResponse lastExceptionConvert(Message<?> message) {
        //处理交易异常
        CommonResponse CommonResponse = new CommonResponse();
        String resultCode = ResponseCodeConstant.SYS_EXCEPTION.getResponseCode();
        String resultDesc = ResponseCodeConstant.SYS_EXCEPTION.getResponseDesc();
        Object exception = message.getPayload();

        if (exception instanceof MessageHandlingException) {
            MessageHandlingException messageHandlingException = (MessageHandlingException) message.getPayload();

            //头部信息获取
            GenericMessage genericMessage = (GenericMessage) messageHandlingException.getFailedMessage();
            MessageHeaders messageHeaders = genericMessage.getHeaders();
            String traceId = messageHeaders.get("traceId") != null ? messageHeaders.get("traceId").toString() : "";
            String channelCode = messageHeaders.get("channelCode") != null ? messageHeaders.get("channelCode").toString() : "";
            String operLevel = messageHeaders.get("operLevel") != null ? messageHeaders.get("operLevel").toString() : "";
            String appname = messageHeaders.get("appname") != null ? messageHeaders.get("appname").toString() : "";
            String all = traceId + "," + appname + "," + channelCode + "," + operLevel;

            Throwable throwable = messageHandlingException.getCause(); //获取异常信息
            if (throwable instanceof CommonException) {
                CommonException commonException = (CommonException) throwable;
                resultCode = commonException.getCode();
                resultDesc = commonException.getMessage();
                log.error("[{}] 项目异常,响应编码：{},响应原因：{},堆栈：", all, resultCode, resultDesc, throwable);
            } else if (throwable instanceof ValidationException) {
                ValidationException validationException = (ValidationException) throwable;
                resultCode = ResponseCodeConstant.REQUEST_ILLEGAL.getResponseCode();
                resultDesc = validationException.getMessage();
                log.error("[{}] 验证异常,响应编码：{},响应原因：{},堆栈：",  all, resultCode, resultDesc, throwable);
            } else {
                log.error("[{}] 未知异常,响应编码：{},响应原因：{},堆栈：", all,  resultCode, throwable, throwable);
            }
        } else {
            log.error("未知异常,响应编码：{},响应原因：", resultCode, exception);
        }
        CommonResponse.setCode(resultCode);
        CommonResponse.setContent(resultDesc);
        return CommonResponse;
    }


}
