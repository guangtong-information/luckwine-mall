package com.luckwine.acct.service;

import com.luckwine.acct.AcctApplication;
import com.luckwine.acct.enums.AbilityCode;
import com.luckwine.acct.handle.local.AcctOperOpenAcctService;
import com.luckwine.acct.model.request.AcctOperRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.entitybase.response.CommonResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author hao
 * @create 2018/9/13
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcctApplication.class)
@WebAppConfiguration
public class AcctOperServiceTest {

    @Resource
    private AcctOperOpenAcctService acctOperOpenAcctService;

    @Test
    public void testOpenAcct() {
        CommonRequest<AcctOperRequest> request = new CommonRequest<>();
        request.setTraceId(SequenceUtil.genSequence(SequenceCode.TRACEID, "1"));
        AcctOperRequest acctOperRequest = new AcctOperRequest();
        acctOperRequest.setAcctName("winlone");
        acctOperRequest.setAcctTypeCode("0001");
        acctOperRequest.setLoginAccount("15918837235");

        List<String> abilityCodeList = Arrays.asList(AbilityCode.RECHARGE.getCode(), AbilityCode.CONSUME.getCode());
        acctOperRequest.setAbilityCodeList(abilityCodeList);

        request.setRequest(acctOperRequest);

        CommonResponse<String> response = acctOperOpenAcctService.call(request);

        log.info("账户能力配置：{}", response.toString());
    }
}

