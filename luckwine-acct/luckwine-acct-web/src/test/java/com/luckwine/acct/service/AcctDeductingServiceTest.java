package com.luckwine.acct.service;

import com.luckwine.acct.AcctApplication;
import com.luckwine.acct.model.request.AcctDeductingRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.acct.service.trans.AccountDeductingService;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author barryng
 * @date 2018-10-14 16:24
 */
@Slf4j
@Data
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcctApplication.class)
@WebAppConfiguration
public class AcctDeductingServiceTest {

    @Resource
    private AccountDeductingService accountDeductingService;

    /**
     * 提现测试
     */
    @Test
    public void testWithdraw() {
        CommonRequest<AcctDeductingRequest> commonRequest = new CommonRequest<>();
        AcctDeductingRequest request = new AcctDeductingRequest();
        request.setPayerAcctCode("1001808171004311275576607");
        request.setTrsAmount(new BigDecimal(1));
        request.setSummary("消费" + request.getTrsAmount() + "元");
        request.setRequestSeq("111111");
        request.setExtTrsSeq("222222");
        commonRequest.setRequest(request);
        CommonResponse<TransRes> res = accountDeductingService.withdraw(commonRequest);

        log.info("账户出账:{}", res.toString());
    }

    /**
     * 消费测试
     */
    @Test
    public void testConsume() {
        CommonRequest<AcctDeductingRequest> commonRequest = new CommonRequest<>();
        AcctDeductingRequest request = new AcctDeductingRequest();
        request.setPayerAcctCode("1001808171004311275576607");
        request.setTrsAmount(new BigDecimal(1));
        request.setSummary("消费" + request.getTrsAmount() + "元");
        request.setRequestSeq("111111");
        request.setExtTrsSeq("222222");
        commonRequest.setRequest(request);
        CommonResponse<TransRes> res = accountDeductingService.consume(commonRequest);

        log.info("账户出账:{}", res.toString());
    }
}
