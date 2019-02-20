package com.luckwine.trade.integration.router;


import com.luckwine.parent.entitybase.exception.CommonException;
import com.luckwine.trade.integration.carrier.BaseCarrier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 请求路由选择
 * Created by Winlone on 2018/9/20.
 */
@Service
@Slf4j
public class RequestRouter {

    public String route(BaseCarrier context) throws CommonException {
        return context.getIntegrationChannel();
    }
}
