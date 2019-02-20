package com.luckwine.synthesize.handle.remote;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.luckwine.parent.entitybase.constant.ResponseCodeConstant;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import com.luckwine.synthesize.config.SmsProperties;
import com.luckwine.synthesize.dao.SmsLogMapper;
import com.luckwine.synthesize.model.base.SmsLog;
import com.luckwine.synthesize.model.request.SmsReq;
import com.luckwine.synthesize.util.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

@Service
@Slf4j
public class SmsSendRemoteService extends SingleTemplate<SmsReq,Boolean>{

    @Autowired
    private SmsProperties smsProperties;

    @Resource
    private SmsLogMapper smsLogMapper;

    @Override
    protected Boolean callInner(CommonRequest<SmsReq> request) throws Exception {

        UUID uuid = UUID.randomUUID();

        // 校验短信
        SmsLog smsLog = handleSmsLog(request.getRequest(),uuid);

        smsLogMapper.insert(smsLog);

        //调用玄武短信发送
        if(smsProperties.getActive() && smsLog.getResultCode() == "0"){
            GsmsResponse response = sendSmsUseXuanWu(smsLog,uuid);
            if(response == null){
                smsLog.setResultCode( ResponseCodeConstant.SYS_EXCEPTION.getResponseCode());
                smsLog.setResultMsg( ResponseCodeConstant.SYS_EXCEPTION.getResponseDesc());
            }else{
                //0：成功 其他失败
                smsLog.setResultCode( String.valueOf(response.getResult()));
                smsLog.setResultMsg(response.getMessage());
            }

            smsLogMapper.updateByPrimaryKey(smsLog);
        }

        if (smsLog.getResultCode().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验短信
     * @param smsReq
     * @param uuid
     * @return
     */
    private SmsLog handleSmsLog(SmsReq smsReq,UUID uuid ) {
        SmsLog smsLog = new SmsLog();
        smsLog.setId(uuid.toString());
        smsLog.setSmsCode(smsReq.getSmsCodeEnum().getCode());
        smsLog.setMobile(smsReq.getMobile());
        smsLog.setResultCode("0");
        smsLog.setResultMsg("等待发送");
        String content = smsReq.getContent();

        //获取模板内容
        String templateContent = SmsUtils.getSmsTemplateMap().get(smsLog.getSmsCode());
        //判断模板是否有占位符
        int index = templateContent.lastIndexOf("}");

        if(index < 0){
            //无占位符
            smsLog.setContent(templateContent);
        } else {
            //有占位符，但传进来的参数为空
            if(StringUtils.isBlank(content)){
                smsLog.setResultCode("-1");
                smsLog.setResultMsg("短信内容参数错误！");
                smsLog.setContent(content);
            } else {
                String[] arr = content.split("\\|");
                int num = Integer.valueOf(templateContent.substring(index-1,index))+1;
                content = MessageFormat.format(templateContent,arr);
                smsLog.setContent(content);
                if(arr.length != num){
                    smsLog.setResultCode("-1");
                    smsLog.setResultMsg("短信内容参数不全");
                }
            }
        }

        if(StringUtils.isBlank(smsLog.getMobile()) || !smsLog.getMobile().matches("1[3-9][0-9]{9}")){
            smsLog.setResultCode("-1");
            smsLog.setResultMsg("手机号校验不通过");
        }

        return smsLog;
    }

    /**
     * 玄武发送短信
     * @param
     * @return
     */
    private GsmsResponse sendSmsUseXuanWu(SmsLog smsLog, UUID uuid) {
        try {
            Account ac = new Account(smsProperties.getUsername(), smsProperties.getPassword());
            PostMsg pm = new PostMsg();
            pm.getCmHost().setHost(smsProperties.getCmHost().split(":")[0], Integer.valueOf(smsProperties.getCmHost().split(":")[1]));//设置网关的IP和port，用于发送信息
            pm.getWsHost().setHost(smsProperties.getWsHost().split(":")[0], Integer.valueOf(smsProperties.getWsHost().split(":")[1]));//设置网关的 IP和port，用于获取账号信息、上行、状态报告等等
            MTPack pack = new MTPack();
            pack.setBatchID(uuid);
            pack.setBatchName("普通短信");
            pack.setMsgType(MTPack.MsgType.SMS);
            pack.setBizType(0);
            pack.setDistinctFlag(false);
            ArrayList<MessageData> msgs = new ArrayList<MessageData>();
            pack.setSendType(MTPack.SendType.MASS);
            msgs.add(new MessageData(smsLog.getMobile(), smsLog.getContent()));
            pack.setMsgs(msgs);
            log.info("玄武短信发送", uuid.toString());
            GsmsResponse resp = pm.post(ac, pack);
            return resp;
        } catch (Exception e) {
            log.error("发送短信发生错误", e);
            return null;
        }
    }
}
