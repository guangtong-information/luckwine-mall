package com.luckwine.acct.service;

import com.luckwine.acct.AcctApplication;
import com.luckwine.acct.model.request.AcctDepositRequest;
import com.luckwine.acct.model.response.TransRes;
import com.luckwine.acct.service.trans.AccountDepositService;
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

@Slf4j
@Data
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcctApplication.class)
@WebAppConfiguration
public class AccountDepositServiceTest {


    @Resource
    AccountDepositService accountDepositService;

    @Test
    public void testDeposit() {

        CommonRequest<AcctDepositRequest> request = new CommonRequest<AcctDepositRequest>();
        AcctDepositRequest acctDepositRequest = new AcctDepositRequest();
        acctDepositRequest.setRequestSeq("1");
        acctDepositRequest.setTrsAmount(new BigDecimal(1));
        acctDepositRequest.setSummary("充值" + acctDepositRequest.getTrsAmount() + "元");
        acctDepositRequest.setExtTrsSeq("2");
        acctDepositRequest.setPayeeAcctCode("1001811041627156060561002");
        request.setRequest(acctDepositRequest);
        CommonResponse<TransRes> response = accountDepositService.deposit(request);

        log.info("账户入账:{}", response.toString());
    }


}
